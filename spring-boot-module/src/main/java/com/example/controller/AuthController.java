package com.example.controller;

import com.example.dto.SignInRequest;
import com.example.dto.SignInResponse;
import com.example.dto.UserRequest;
import com.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public SignInResponse login(@RequestBody SignInRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRequest request) {
        authService.register(request);
    }
}