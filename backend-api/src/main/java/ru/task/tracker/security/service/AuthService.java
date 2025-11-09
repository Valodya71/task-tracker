package ru.task.tracker.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.task.tracker.security.dto.LoginRequest;
import ru.task.tracker.security.jwt.JwtService;
import ru.task.tracker.security.model.User;
import ru.task.tracker.security.repository.UserRepository;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public String registerUser(String username, String email, String password) {
        if (userRepository.existsByUsername(username) ||
                userRepository.existsByEmail(email)) {
            return "User or email already in use";
        }

        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        userRepository.save(user);

        String token = getToken(user.getUsername());

        return "User registered successfully. Token: " + token;
    }

    @Transactional()
    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = getToken(user.getUsername());
        return "Login successful. Token: " + token;
    }

    private String getToken(String username) {
        return jwtService.generateToken(username);
    }


}
