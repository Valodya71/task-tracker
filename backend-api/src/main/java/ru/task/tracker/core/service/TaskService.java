package ru.task.tracker.core.service;

import ru.task.tracker.core.dto.TaskRequest;
import ru.task.tracker.core.dto.TaskResponse;

import java.util.List;

public interface TaskService {

    void createTask(Long UserId, TaskRequest taskRequest);

    List<TaskResponse> showAllTasks(Long id);

    void completedTask(Long userId, Long  taskId);

    void deleteTask(Long userId, Long  taskId);

}
