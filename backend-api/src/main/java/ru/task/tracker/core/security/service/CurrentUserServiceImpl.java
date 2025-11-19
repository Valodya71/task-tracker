package ru.task.tracker.core.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.task.tracker.core.security.dto.UserResponse;
import ru.task.tracker.core.security.jwt.utils.JwtTokenUtils;

@Service
@RequiredArgsConstructor
class CurrentUserServiceImpl implements CurrentUserService {

    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public ResponseEntity<?> getCurrentUser(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtTokenUtils.getUsername(token);

        return userService.findByUsername(username)
                .map(user -> ResponseEntity.ok(new UserResponse(user.getId(), user.getEmail())))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public String getCurrentUserName(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return jwtTokenUtils.getUsername(token);
    }

}
