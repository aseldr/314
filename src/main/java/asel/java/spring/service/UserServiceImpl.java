package asel.java.spring.service;

import asel.java.spring.model.User;
import asel.java.spring.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Transactional
    @Override
    public void saveUser(User existingUser, User user) {
        if (existingUser != null && (user.getPassword() == null || user.getPassword().isEmpty())) {
            user.setPassword(existingUser.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (existingUser != null) {
            user.setId(existingUser.getId());
        }
        if (existingUser != null && (user.getRoles() == null || user.getRoles().isEmpty())) {
            user.setRoles(existingUser.getRoles());
        }
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByFirstname(String name) {
        return userRepository.findByFirstname(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public void update(Long id, User user) throws EntityNotFoundException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRoles(user.getRoles());
        userRepository.save(existingUser);
    }
}