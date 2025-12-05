package ru.task.tracker.core.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.task.tracker.core.entity.User;
import ru.task.tracker.core.exception.UserNotFoundException;
import ru.task.tracker.core.repository.UserRepository;
import ru.task.tracker.core.security.dto.RegisterUserRequest;
import ru.task.tracker.core.security.entity.details.SecurityUser;
import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //private final RoleService roleService;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User createNewUser(RegisterUserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return userRepository.save(user);
    }


    @Override
    public SecurityUser loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(
                 username));

        return new SecurityUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList() // без ролей
        );
    }

}
