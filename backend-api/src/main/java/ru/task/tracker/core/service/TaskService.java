package ru.task.tracker.core.service;

import ru.task.tracker.core.dto.TaskRequest;
import ru.task.tracker.core.entity.Task;
import ru.task.tracker.core.entity.User;

import java.util.List;

public interface TaskService {

    void createTask(User user, TaskRequest taskRequest);

    void deleteTask(Task task);

    List<Task> showAllTasks(Long id);

}
