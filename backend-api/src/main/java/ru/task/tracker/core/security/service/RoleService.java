package ru.task.tracker.core.security.service;


import org.springframework.stereotype.Service;

@Service
public class RoleService{

    // private final RoleRepository

    public String getUserRole() {
        return "ROLE_USER";
    }


}
