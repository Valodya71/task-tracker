package ru.task.tracker.core.security.service;

import org.springframework.http.ResponseEntity;

public interface CurrentUserService{

    ResponseEntity<?> getCurrentUser(String authHeader);

    String getCurrentUserName(String authHeader);

}
