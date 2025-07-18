package com.example.repository;

import com.example.model.User;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements IRepository {
    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        Logger logger = LoggerFactory.getLogger(UserRepository.class);
        logger.info("!!! Adding initial users to the repository");
        users.add(new User(1, "Alice1", "alice@gmail.com", "password123", "Alice", "Smith"));
        users.add(new User(2, "Bob2", "bob@gmail.com", "password123", "Bob", "Johnson"));
        users.add(new User(3, "Charlie3", "char@gmail.com", "password123", "Charlie", "Brown"));
        users.add(new User(4, "Dia4", "diana@gmail.com", "password123", "Diana", "Prince"));
        users.add(new User(5, "Alan5", "alan@gmail.com", "password123", "Alan", "Turing"));
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    public void save(User user) {
        users.add(user);
    }
}
