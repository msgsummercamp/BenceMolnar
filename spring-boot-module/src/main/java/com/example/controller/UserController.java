package com.example.controller;

import com.example.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.IService;
import com.example.service.UserService;
import java.util.List;

@RestController
public class UserController {
    private final IService userService = new UserService();
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
