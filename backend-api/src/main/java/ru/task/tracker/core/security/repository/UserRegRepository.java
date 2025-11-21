package ru.task.tracker.core.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.task.tracker.core.security.entity.UserReg;

import java.util.Optional;

@Repository
public interface UserRegRepository extends JpaRepository<UserReg, Long> {

    Optional<UserReg> findByUsername(String username);

    Optional<UserReg> findByEmail(String email);
}
