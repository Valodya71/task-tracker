package ru.task.tracker.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.task.tracker.core.dto.Token;
import ru.task.tracker.security.jwt.utils.JwtTokenUtils;

@Service
@RequiredArgsConstructor
public class CurrentUserService {

    private final JwtTokenUtils jwtTokenUtils;

    public String getCurrentUsername(Token token) {
        return jwtTokenUtils.getUsername(token.getToken());
    }

}
