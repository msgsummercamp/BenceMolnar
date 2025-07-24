package com.example;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.IRoleRepository;
import com.example.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    void setUp() {
        Role adminRole = new Role(1, "ADMIN");
        Role userRole = new Role(2, "USER");
        roleRepository.save(adminRole);
        roleRepository.save(userRole);
        User user1 = new User(1111111, "Test1", "test1@gmail.com", passwordEncoder.encode("testpass1"), "Test1", "test1", adminRole);
        User user2 = new User(1111112, "Test2", "test2@gmail.com", passwordEncoder.encode("testpass2"), "Test2", "test2", userRole);
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
        assertTrue(passwordEncoder.matches("testpass2",passwordEncoder.encode("testpass2")));
        assertEquals("Test2", foundUser2.get().getFirstName());
        assertEquals("test2", foundUser2.get().getLastName());
    }

    @Test
    void testDeleteUser() {
        Optional<User> userToDelete = userService.findById(1111111);
        assertTrue(userToDelete.isPresent());
        userService.delete(userToDelete.get());
        Optional<User> deletedUser = userService.findById(1111111);
        assertFalse(deletedUser.isPresent());
    }

    @AfterEach
    void tearDown() {
        Optional<User> user2ToDelete = userService.findById(1111112);
        userService.delete(user2ToDelete.get());
        roleRepository.deleteAll();
    }
}