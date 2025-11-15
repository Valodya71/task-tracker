package ru.task.tracker.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.task.tracker.core.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {



}
