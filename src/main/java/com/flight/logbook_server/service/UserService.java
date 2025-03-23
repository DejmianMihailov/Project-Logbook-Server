package com.flight.logbook_server.service;

import com.flight.logbook_server.model.User;
import com.flight.logbook_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(User user) {
        if (existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username is already taken.");
        }
        if (user.getEmail() != null && existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        return userRepository.save(user);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public void deleteUserByUsername(String username) {
        userRepository.deleteByUsername(username);
    }
}
