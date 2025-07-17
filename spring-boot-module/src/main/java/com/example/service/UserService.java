package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.repository.IRepository;

import java.util.List;

public class UserService implements IService {
    private IRepository userRepository = new UserRepository();

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
