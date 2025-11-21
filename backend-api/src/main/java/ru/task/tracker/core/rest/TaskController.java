package ru.task.tracker.core.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.task.tracker.core.dto.TaskRequest;
import ru.task.tracker.core.dto.TaskResponse;
import ru.task.tracker.core.security.service.CurrentUserService;
import ru.task.tracker.core.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final CurrentUserService currentUserService;


    @PostMapping("/task")
    public ResponseEntity<?> createTask(@RequestHeader("Authorization") String authHeader,
                                        @RequestBody TaskRequest taskRequest) {
        Long UserId = currentUserService.getCurrentUserId(authHeader);

        taskService.createTask(UserId, taskRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tasks")
    public ResponseEntity<?> showUserTasks(@RequestHeader("Authorization") String authHeader) {
        Long userId = currentUserService.getCurrentUserId(authHeader);

        List<TaskResponse> tasks = taskService.showAllTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/task/{taskId}")
    public ResponseEntity<?> completedTask(@RequestHeader("Authorization") String authHeader,
                                              @PathVariable Long taskId){
        Long userId = currentUserService.getCurrentUserId(authHeader);

        taskService.completedTask(userId, taskId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/task/{taskId}")
    public ResponseEntity<?> deleteTask(@RequestHeader("Authorization") String authHeader,
                           @PathVariable Long taskId){
        Long userId = currentUserService.getCurrentUserId(authHeader);

        taskService.deleteTask(userId, taskId);
        return ResponseEntity.ok().build();
    }


}
