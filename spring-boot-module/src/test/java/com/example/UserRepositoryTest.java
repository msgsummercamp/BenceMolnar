package com.example;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    void saveAndFindAll() {
        User user1 = new User(1, "Alice1", "alice@gmail.com", "password1", "Alice", "Smith");
        User user2 = new User(2, "Bob2", "bob@gmail.com", "password2", "Bob", "Johnson");

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> users = userRepository.findAll();
        // size() will be equal to 6 because the repository already has 4 initial users
        assertEquals(7, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }
}