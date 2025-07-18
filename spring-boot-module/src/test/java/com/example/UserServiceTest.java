package com.example;

import com.example.model.User;
import com.example.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveFindDeleteUser() {
        User user = new User(1, "Alice1", "alice@gmail.com", "password123", "Alice", "Smith");
        userService.save(user);

        Optional<User> found = userService.findByEmail("alice@gmail.com");
        assertTrue(found.isPresent());
        assertEquals("Alice1", found.get().getUsername());

        userService.delete(user);
        Optional<User> foundAfterDelete = userService.findByEmail(user.getEmail());
        assertFalse(foundAfterDelete.isPresent());
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User(1111111, "Test1", "test1@gmail.com", "testpass1", "Test1", "test1");
        User user2 = new User(1111112, "Test2", "test2@gmail.com", "testpass2", "Test2", "test2");
        userService.save(user1);
        userService.save(user2);

        List<User> users = userService.getAllUsers();
        int usersSize = users.size();
        assertTrue(users.size() >= 2);

        userService.delete(user1);
        userService.delete(user2);
        List<User> usersAfterDelete = userService.getAllUsers();
        assertEquals(usersSize - 2, usersAfterDelete.size());
    }
}