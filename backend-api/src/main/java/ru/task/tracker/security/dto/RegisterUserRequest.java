package ru.task.tracker.security.dto;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private String username;

    private String password;

    private String confirmPassword;

    private String email;

}
