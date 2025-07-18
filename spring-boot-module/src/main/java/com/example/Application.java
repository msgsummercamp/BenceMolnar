package com.example;

import com.example.model.User;
import com.example.repository.IDBRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.example")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner initUsers(IDBRepository userRepository) {
        return args -> {
            userRepository.save(new User(1, "Alice1", "alice@gmail.com", "password123", "Alice", "Smith"));
            userRepository.save(new User(2, "Bob2", "bob@gmail.com", "password456", "Bob", "Long"));
            userRepository.save(new User(3, "Charlie3", "charlie@gmail.com", "password789", "Charlie", "Sigma"));
        };
    }
}