package com.example;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsersList() {
        List<User> users = Arrays.asList(new User(1, "Alice1", "alice@gmail.com", "password123", "Alice", "Smith"),
                new User(2, "Bob2", "bob@gmail.com", "password456", "Bob", "Johnson"));
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void saveAndReturnUser() {
        User user = new User(1, "Alice1", "alice@gmail.com", "password1", "Alice", "Smith");
        doNothing().when(userRepository).save(user);

        User result = userService.saveUser(user);

        assertEquals("Alice", result.getFirstName());
        verify(userRepository, times(1)).save(user);
    }
}