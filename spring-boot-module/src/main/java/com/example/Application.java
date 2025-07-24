package com.example;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.IDBRepository;
import com.example.repository.IRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = "com.example")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner initUsers(IDBRepository userRepository, IRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Role adminRole = roleRepository.save(new Role(1, "ADMIN"));
            Role userRole = roleRepository.save(new Role(2, "USER"));

            userRepository.save(new User(1, "Alice1", "alice@gmail.com", passwordEncoder.encode("password123"), "Alice", "Smith", adminRole));
            userRepository.save(new User(2, "Bob2", "bob@gmail.com", passwordEncoder.encode("password456"), "Bob", "Long", userRole));
            userRepository.save(new User(3, "Charlie3", "charlie@gmail.com", passwordEncoder.encode("password789"), "Charlie", "Sigma", userRole));
        };
    }
}