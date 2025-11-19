package ru.task.tracker.core.repository;

import org.springframework.data.repository.CrudRepository;
import ru.task.tracker.core.entity.User;

import java.util.Optional;

public interface UserRepositoryNP extends CrudRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

}
