package ru.task.tracker.core.repository;

import org.springframework.data.repository.CrudRepository;
import ru.task.tracker.core.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);

}
