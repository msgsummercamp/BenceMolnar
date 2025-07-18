package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Data
@AllArgsConstructor
@Entity
@Table(name= "user")
public class User {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name= "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;

    public User() {
        // Default constructor for JPA
    }
}
