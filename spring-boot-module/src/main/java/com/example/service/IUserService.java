package com.example.service;

import com.example.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User save(User user);
    Optional<User> findById(Integer id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> getAllUsers();
    void delete(User user);
    Optional<User> update(Integer id, User user);
    Integer getUserCount();
}
