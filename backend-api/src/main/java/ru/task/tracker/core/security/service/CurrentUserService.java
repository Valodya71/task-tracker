package ru.task.tracker.core.security.service;

import org.springframework.http.ResponseEntity;

public interface CurrentUserService{

    Long getCurrentUserId(String authHeader)
            ;
    ResponseEntity<?> getCurrentUser(String authHeader);

    String getCurrentUsername(String authHeader);

}
