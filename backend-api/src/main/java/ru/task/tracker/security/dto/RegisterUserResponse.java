package ru.task.tracker.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserResponse {

        private Long id;

        private String username;

        private String email;


}
