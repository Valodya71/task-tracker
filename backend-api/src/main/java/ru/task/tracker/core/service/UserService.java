package ru.task.tracker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.task.tracker.core.dto.Token;
import ru.task.tracker.core.entity.User;
import ru.task.tracker.core.repository.UserRepository;
import ru.task.tracker.security.service.CurrentUserService;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CurrentUserService currentUserService;

    private final UserRepository userRepository;

    public Optional<User> getUser(Token token) {
        String currentUsername = currentUserService.getCurrentUsername(token);
        return userRepository.findUserByUsername(currentUsername);
    }


}
