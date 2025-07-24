package com.example.service;

import com.example.config.JwtConfig;
import com.example.dto.SignInRequest;
import com.example.dto.SignInResponse;
import com.example.dto.UserRequest;
import com.example.model.Role;
import com.example.model.User;
import com.example.repository.IDBRepository;
import com.example.repository.IRoleRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private IDBRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Key key;

    @Autowired
    public AuthServiceImpl(JwtConfig jwtConfig) {
        this.key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
    }


    @Override
    public SignInResponse login(SignInRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", List.of(user.getRole().getName()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();
        return new SignInResponse(token, List.of(user.getRole().getName()));
    }

    @Override
    public void register(UserRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.findById(request.getId()).isPresent()) {
            throw new RuntimeException("User ID already exists");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("User role not found"));

        User user = new User(
                request.getId(),
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getFirstname(),
                request.getLastname(),
                userRole
        );
        userRepository.save(user);
    }
}