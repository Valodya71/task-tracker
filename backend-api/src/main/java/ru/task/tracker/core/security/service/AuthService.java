package ru.task.tracker.core.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.task.tracker.core.entity.User;
import ru.task.tracker.core.exception.BadRequestException;
import ru.task.tracker.core.security.dto.JwtResponse;
import ru.task.tracker.core.security.dto.LoginUserRequest;
import ru.task.tracker.core.security.dto.RegisterUserRequest;
import ru.task.tracker.core.security.dto.RegisterUserResponse;
import ru.task.tracker.core.security.entity.details.SecurityUser;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final ru.task.tracker.core.security.jwt.utils.JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createNewUser(@RequestBody RegisterUserRequest userRequest) {
        if (!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
            throw new BadRequestException("the passwords don't match");
        }
        if (userService.findByUsername(userRequest.getUsername()).isPresent()) {
            throw new BadRequestException("Username with that name already exists");
        }
        if (userService.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new BadRequestException("Email with that email already exists");
        }

        User user = userService.createNewUser(userRequest);

        return ResponseEntity.ok(new RegisterUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        ));
    }

    public ResponseEntity<?> createAuthToken(@RequestBody LoginUserRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityUser userDetails = userService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }


}