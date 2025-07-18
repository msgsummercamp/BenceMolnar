package com.example.model;

import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
