package com.example.controller;

import com.example.model.User;
import com.example.service.IUserService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private final IUserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        logger.info("Creating user: {}", user);
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Integer id) {
        logger.info("Finding user by ID: {}", id);
        return userService.findById(id);
    }

    @GetMapping("/{username}")
    public Optional<User> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/email/{email}")
    public Optional<User> findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{email}")
    public void delete(@PathVariable String email) {
        logger.info("Deleting user with email: {}", email);
        Optional<User> user = userService.findByEmail(email);
        user.ifPresent(userService::delete);
    }

    @PostMapping("/update/{id}")
    public Optional<User> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        logger.info("Updating user with email: {}", id);
        return userService.update(id, updatedUser);
    }

    @GetMapping("/count")
    public Integer getUserCount() {
        return userService.getUserCount();
    }
}
