package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IDBRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String userName);
    Optional<User> findByEmail(String email);

    @Query("SELECT COUNT(u) FROM User u ")
    Integer getUserCount();
}
