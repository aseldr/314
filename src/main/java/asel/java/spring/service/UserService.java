package asel.java.spring.service;

import asel.java.spring.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    User getById(Long id);

    void saveUser(User existingUser, User user);

    void deleteUser(User user);

    User getByFirstname(String name);

    Optional<User> getUserByEmail(String email);

    void update(Long id, User user);
}