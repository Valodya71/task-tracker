package ru.task.tracker.security.dto;

import lombok.Data;

@Data
public class LoginUserRequest {

    private String username;
    private String password;

}
