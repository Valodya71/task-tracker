package ru.task.tracker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.task.tracker.core.dto.TaskRequest;
import ru.task.tracker.core.dto.TaskResponse;
import ru.task.tracker.core.entity.Task;
import ru.task.tracker.core.entity.User;
import ru.task.tracker.core.exception.UserNotFoundException;
import ru.task.tracker.core.repository.TaskRepository;
import ru.task.tracker.core.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CurrentTaskUser currentTaskUser;

    public void createTask(Long userId, TaskRequest taskRequest) {
        Task task = new Task();

        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setCompleted(false);
        task.setFinishTime(LocalDateTime.MAX);

        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId) );
        task.setUser(user);

        taskRepository.save(task);
    }


    public List<TaskResponse> showAllTasks(Long id) {
        return taskRepository.findAllByUserId(id).stream()
                .filter(task -> !task.getCompleted())
                .map(this::convertToResponseTask)
                .toList();
    }

    @Override
    @Transactional
    public void completedTask(Long userId, Long taskId) {
        if(currentTaskUser.checking(userId, taskId))
            taskRepository.findById(taskId).ifPresent(value -> value.setCompleted(true));
    }

    @Override
    public void deleteTask(Long userId, Long taskId) {
        if(currentTaskUser.checking(userId, taskId))
            taskRepository.deleteById(taskId);
    }

    private TaskResponse convertToResponseTask(Task task) {
        TaskResponse taskResponse = new TaskResponse();

        taskResponse.setId(task.getId());
        taskResponse.setTitle(task.getTitle());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setFinishTime(null);
        taskResponse.setCompleted(task.getCompleted());

        return taskResponse;
    }

}
