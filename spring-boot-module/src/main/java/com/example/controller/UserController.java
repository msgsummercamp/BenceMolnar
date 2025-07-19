package com.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import com.example.model.User;
import com.example.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Tag(name="User Management", description = "Operations related to user management")
@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a new user", description = "Creates a new user in the system.",
            responses = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user data provided"),
            })
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        logger.info("Creating user: {}", user);
        User createdUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(summary = "Find user by ID", description = "Retrieves a user by their unique ID.",
            responses = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
            })
    @GetMapping("/id/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        logger.info("Finding user by ID: {}", id);
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Find user by username", description = "Retrieves a user by their username.",
            responses = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
            })
    @GetMapping("/user/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Find user by email", description = "Retrieves a user by their email address.",
            responses = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
            })
    @GetMapping("/email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Find all users", description = "Retrieves a paginated list of all users.",
            responses = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No users found")
            })
    @GetMapping("/page/{page}/size/{size}")
    public ResponseEntity<List<User>> findAll(@PathVariable int page, @PathVariable int size) {
        logger.info("Retrieving users - page: {}, size: {}", page, size);
        Page<User> users = userService.getUsersByPage(page, size);
        return ResponseEntity.ok(users.getContent());
    }

    @Operation(summary = "Delete user by ID", description = "Deletes a user by their unique ID.",
            responses = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
            })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        logger.info("Deleting user with id: {}", id);
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            userService.delete(user.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Update user by ID", description = "Updates a user's information by their unique ID.",
            responses = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
            })
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @Valid @RequestBody User updatedUser) {
        logger.info("Updating user with id: {}", id);
        Optional<User> user = Optional.ofNullable(userService.update(id, updatedUser));
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Partially update user by ID", description = "Partially updates a user's information by their unique ID. Null-values will not be updated.",
            responses = {
            @ApiResponse(responseCode = "200", description = "User partially updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
            })
    @PatchMapping("/update_part/{id}")
    public ResponseEntity<User> updateUserPartially(@PathVariable Integer id, @RequestBody User updatedUser) {
        logger.info("Partially updating user with ID: {}", id);
        Optional<User> user = Optional.ofNullable(userService.updatePartially(id, updatedUser));
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Get user count", description = "Retrieves the total number of users in the system.",
            responses = {
            @ApiResponse(responseCode = "200", description = "User count retrieved successfully"),
            })
    @GetMapping("/count")
    public ResponseEntity<Integer> getUserCount() {
        return ResponseEntity.ok(userService.getUserCount());
    }
}
