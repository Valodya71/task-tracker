package ru.task.tracker.core.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.task.tracker.core.dto.TaskRequest;
import ru.task.tracker.core.entity.Task;
import ru.task.tracker.core.security.service.CurrentUserService;
import ru.task.tracker.core.service.TaskService;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final CurrentUserService currentUserService;


    @GetMapping("/task")
    public ResponseEntity<?> createTask("Authorization") String authHeader, TaskRequest taskRequest) {
        String currentUserName = currentUserService.getCurrentUserName(taskService);
        taskService.createTask(currentUserName, taskRequest);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String authHeader) {
        return currentUserService.getCurrentUser(authHeader);
    }

}
