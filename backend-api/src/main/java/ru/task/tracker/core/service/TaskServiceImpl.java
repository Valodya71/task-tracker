package ru.task.tracker.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.task.tracker.core.dto.TaskRequest;
import ru.task.tracker.core.entity.Task;
import ru.task.tracker.core.entity.User;
import ru.task.tracker.core.repository.TaskRepository;
import ru.task.tracker.core.repository.UserRepositoryNP;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepositoryNP userRepository;

    public void createTask(String username, TaskRequest taskRequest) {
        Task task = new Task();

        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setCompleted(false);
        task.setFinishTime(LocalDateTime.MAX);

        User user =
        task.setUser(user);

        taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public List<Task> showAllTasks(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found") );
        return user.getTasks();
    }

}
