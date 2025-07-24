package com.example.dto;

import com.example.model.Role;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "ID is required")
    @Pattern(regexp = "^[0-9]+$", message = "ID must be a number")
    private Integer id;

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,30}$", message = "Username")
    private String username;

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "At least 6 characters long")
    private String password;

    @NotBlank(message = "Firstname is required")
    @Size(max = 255, message = "Max. 255 characters long")
    private String firstname;

    @NotBlank(message = "Lastname is required")
    @Size(max = 255, message = "Max. 255 characters long")
    private String lastname;

    @NotBlank(message = "Role is required (eg. ADMIN, USER)")
    private Role role;
}