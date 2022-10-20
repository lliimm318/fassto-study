package com.example.study.controller;

import com.example.study.domain.service.UserService;
import com.example.study.payload.request.AuthRequest;
import com.example.study.payload.request.RegisterRequest;
import com.example.study.payload.response.TokenResponse;
import com.example.study.payload.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        userService.register(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody AuthRequest request) {
        return userService.logIn(request);
    }

    @PostMapping("/refresh")
    public TokenResponse refresh(@RequestHeader("refresh-token") String refreshToken) {
        return userService.TokenRefresh(refreshToken);
    }

    @GetMapping("/user")
    public UserResponse getUser() {
        return userService.getUserInfo();
    }

}
