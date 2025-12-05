package ru.task.tracker.core.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.task.tracker.core.security.dto.LoginUserRequest;
import ru.task.tracker.core.security.dto.RegisterUserRequest;
import ru.task.tracker.core.security.service.AuthService;
import ru.task.tracker.core.security.service.CurrentUserService;


@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final CurrentUserService currentUserService;

    @PostMapping("/auth/registration")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody RegisterUserRequest userRequest) {
        return authService.createNewUser(userRequest);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> createAuthToken(@RequestBody LoginUserRequest loginRequest) {
        return authService.createAuthToken(loginRequest);
    }

    @GetMapping("/userid")
    public ResponseEntity<?> getUserId(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(currentUserService.getCurrentUserId(authHeader));
    }

}
