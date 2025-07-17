package com.example.repository;

import com.example.model.User;
import java.util.List;

public class UserRepository implements IRepository {
    @Override
    public List<User> findAll() {
        return List.of();
    }
}
