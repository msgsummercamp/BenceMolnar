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
        users.add(new User(1, "Alice"));
        users.add(new User(2, "Bob"));
        users.add(new User(3, "Charlie"));
        users.add(new User(4, "Diana"));
        users.add(new User(5, ""));
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    public void save(User user) {
        users.add(user);
    }
}
