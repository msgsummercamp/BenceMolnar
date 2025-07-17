package com.example.controller;

import com.example.model.User;
import com.example.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final IService userService;

    @Autowired
    public UserController(IService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        logger.info("Fetching all users");
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        logger.info("Saving user: {}", user);
        return userService.saveUser(user);
    }
}
