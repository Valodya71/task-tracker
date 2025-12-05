package ru.task.tracker.core.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 6, max = 100, message = "Username maximum 100 length")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 25, message = "Password minimum length 6 and maximum 25")
    private String password;

    private String confirmPassword;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Incorrect email")
    private String email;

}
