package com.example.repository;

import com.example.model.User;
import java.util.List;

public interface IRepository {
    List<User> findAll();
}
