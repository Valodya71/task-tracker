package ru.task.tracker.core.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.task.tracker.core.exception.UserNotFoundException;
import ru.task.tracker.core.security.dto.RegisterUserRequest;
import ru.task.tracker.core.security.entity.UserReg;
import ru.task.tracker.core.security.entity.details.SecurityUser;
import ru.task.tracker.core.security.repository.UserRegRepository;

import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRegRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //private final RoleService roleService;

    public Optional<UserReg> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<UserReg> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public UserReg createNewUser(RegisterUserRequest userRequest) {
        UserReg user = new UserReg();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return userRepository.save(user);
    }


    @Override
    public SecurityUser loadUserByUsername(String username) {
        UserReg user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(
                 username));

        return new SecurityUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList() // без ролей
        );
    }

}
