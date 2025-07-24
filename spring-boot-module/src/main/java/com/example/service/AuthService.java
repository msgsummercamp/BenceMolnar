package com.example.service;

import com.example.dto.SignInRequest;
import com.example.dto.SignInResponse;
import com.example.dto.UserRequest;

public interface AuthService {
    SignInResponse login(SignInRequest request);
    void register(UserRequest request);
}