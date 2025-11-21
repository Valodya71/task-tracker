package ru.task.tracker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.task.tracker.core.repository.TaskRepository;

@Component
@RequiredArgsConstructor
class CurrentTaskUser {

    private final TaskRepository taskRepository;

    boolean checking(Long userId, Long taskId) {
        return taskRepository.findById(taskId)
                .map(task -> task.getUser().getId().equals(userId))
                .orElse(false);
    }

}
