package ru.task.tracker.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.task.tracker.security.dto.JwtResponse;
import ru.task.tracker.security.dto.LoginUserRequest;
import ru.task.tracker.security.dto.RegisterUserRequest;
import ru.task.tracker.security.dto.RegisterUserResponse;
import ru.task.tracker.security.entity.User;
import ru.task.tracker.security.exceptions.AppError;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final ru.task.tracker.security.jwt.utils.JwtTokenUtils JwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createNewUser(@RequestBody RegisterUserRequest userRequest) {
        if (!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "the passwords don't match"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(userRequest.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "user with that name already exists"), HttpStatus.BAD_REQUEST);
        }

        User user = userService.createNewUser(userRequest);

        return ResponseEntity.ok(new RegisterUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        ));
    }

    public ResponseEntity<?> createAuthToken(@RequestBody LoginUserRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect login or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
        String token = JwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}