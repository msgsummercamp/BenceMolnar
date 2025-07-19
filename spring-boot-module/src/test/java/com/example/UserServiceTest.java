package com.example;

import com.example.model.User;
import com.example.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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

    @BeforeAll
    static void setUp(@Autowired UserService userService) {
        User user1 = new User(1111111, "Test1", "test1@gmail.com", "testpass1", "Test1", "test1");
        User user2 = new User(1111112, "Test2", "test2@gmail.com", "testpass2", "Test2", "test2");
        userService.save(user1);
        userService.save(user2);
    }

    @Test
    void testFindUser() {
        Optional<User> foundUser1 = userService.findById(1111111);
        assertTrue(foundUser1.isPresent());
        assertEquals("Test1", foundUser1.get().getUsername());
        assertEquals("test1@gmail.com", foundUser1.get().getEmail());
        Optional<User> foundUser2 = userService.findById(1111112);
        assertTrue(foundUser2.isPresent());
        assertEquals("testpass2", foundUser2.get().getPassword());
        assertEquals("Test2", foundUser2.get().getFirstName());
        assertEquals("test2", foundUser2.get().getLastName());
    }

    @Test
    void testFindAllUsers() {
        List<User> users = userService.getAllUsers();
        assertFalse(users.isEmpty());
        assertEquals(users.size() + 2, users.size());
    }

    @Test
    void testDeleteUser() {
        Optional<User> userToDelete = userService.findById(1111111);
        assertTrue(userToDelete.isPresent());
        userService.delete(userToDelete.get());
        Optional<User> deletedUser = userService.findById(1111111);
        assertFalse(deletedUser.isPresent());
    }

    @AfterAll
    static void tearDown(@Autowired UserService userService) {
        Optional<User> user1ToDelete = userService.findById(1111111);
        Optional<User> user2ToDelete = userService.findById(1111112);
        assertTrue(user1ToDelete.isPresent());
        assertTrue(user2ToDelete.isPresent());
        userService.delete(user1ToDelete.get());
        userService.delete(user2ToDelete.get());
    }
}