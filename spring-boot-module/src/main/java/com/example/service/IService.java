package com.example.service;

import com.example.model.User;
import java.util.List;

public interface IService {
    List<User> getAllUsers();
    User saveUser(User user);
}
