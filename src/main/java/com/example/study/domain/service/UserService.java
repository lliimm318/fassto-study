package com.example.study.domain.service;

import com.example.study.payload.request.AuthRequest;
import com.example.study.payload.request.RegisterRequest;
import com.example.study.payload.response.TokenResponse;
import com.example.study.payload.response.UserResponse;

public interface UserService {

    void register(RegisterRequest request);

    TokenResponse logIn(AuthRequest request);

    TokenResponse TokenRefresh(String token);

    UserResponse getUserInfo();

}
