package com.flight.logbook_server.repository;

import com.flight.logbook_server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    void deleteByUsername(String username);

    boolean existsByUsername(String username); // Проверка за съществуване по username
    boolean existsByEmail(String email); // Проверка за съществуване по email
}
