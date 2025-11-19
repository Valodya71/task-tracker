package ru.task.tracker.core.security.dto;

import lombok.Data;

@Data
public class LoginUserRequest {

    private String username;
    private String password;

}
