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
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        logger.info("Finding user by ID: {}", id);
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Delete user by ID", description = "Deletes a user by their unique ID.",
            responses = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
            })
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Integer id) {
        logger.info("Deleting user with id: {}", id);
        try {
            userService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Update user by ID", description = "Updates a user's information by their unique ID.",
            responses = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
            })
    @PutMapping
    public ResponseEntity<User> updateUser(@Valid @RequestBody User updatedUser) {
        logger.info("Updating user with id: {}", updatedUser.getId());
        Optional<User> user = Optional.ofNullable(userService.update(updatedUser.getId(), updatedUser));
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Partially update user by ID", description = "Partially updates a user's information by their unique ID. Null-values will not be updated.",
            responses = {
            @ApiResponse(responseCode = "200", description = "User partially updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
            })
    @PatchMapping
    public ResponseEntity<User> updateUserPartially(@RequestBody User updatedUser) {
        logger.info("Partially updating user with ID: {}", updatedUser.getId());
        Optional<User> user = Optional.ofNullable(userService.updatePartially(updatedUser.getId(), updatedUser));
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

    @GetMapping
    public ResponseEntity<?> getUserByData(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        if (username != null) {
            return userService.findByUsername(username)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } else if (email != null) {
            return userService.findByEmail(email)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } else if (page != null && size != null) {
            Page<User> users = userService.getUsersByPage(page, size);
            return ResponseEntity.ok(users.getContent());
        } else {
            return ResponseEntity.badRequest().body("No valid parameter provided");
        }
    }
}
