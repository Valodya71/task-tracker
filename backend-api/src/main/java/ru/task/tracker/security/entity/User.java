package ru.task.tracker.security.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Username not blank")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Password not blank")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Email not blank")
    @Email(message = "Email not format")
    @Column(name = "email", unique = true, nullable = false)
    private String email;


}
