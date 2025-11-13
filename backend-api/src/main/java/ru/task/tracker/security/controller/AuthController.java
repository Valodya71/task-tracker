package ru.task.tracker.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.task.tracker.security.dto.LoginUserRequest;
import ru.task.tracker.security.dto.RegisterUserRequest;
import ru.task.tracker.security.service.AuthService;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegisterUserRequest userRequest) {
        return authService.createNewUser(userRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody LoginUserRequest loginRequest) {
        return authService.createAuthToken(loginRequest);
    }

}
