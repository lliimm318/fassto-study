package com.example.study.domain.service;

import com.example.study.entity.User;
import com.example.study.exception.UserAlreadyException;
import com.example.study.exception.UserNotFoundException;
import com.example.study.payload.request.AuthRequest;
import com.example.study.payload.request.RegisterRequest;
import com.example.study.payload.response.TokenResponse;
import com.example.study.payload.response.UserResponse;
import com.example.study.repository.UserRepository;
import com.example.study.security.TokenProvider;
import com.example.study.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationFacade facade;

    @Override
    public void register(RegisterRequest request) {
        userRepository.findById(request.getId())
                .orElseThrow(UserAlreadyException::new);
        userRepository.findById(request.getName())
                .orElseThrow(UserAlreadyException::new);

        User user = User.builder()
                .id(request.getId())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
    }

    @Override
    public TokenResponse logIn(AuthRequest request) {
        userRepository.findById(request.getId())
                .filter(u -> passwordEncoder.matches(request.getPassword(), u.getPassword()))
                .orElseThrow(UserNotFoundException::new);

        return TokenResponse.builder()
                .accessToken(tokenProvider.generateAccessToken(request.getId()))
                .refreshToken(tokenProvider.generateRefreshToken(request.getId()))
                .build();
    }

    @Override
    public TokenResponse TokenRefresh(String token) {
        return TokenResponse.builder()
                .accessToken(tokenProvider.generateAccessToken(tokenProvider.getId(token)))
                .refreshToken(token)
                .build();
    }

    @Override
    public UserResponse getUserInfo() {
        return UserResponse.builder()
                .id(facade.getUserId())
                .name(facade.getUser().getName())
                .password(facade.getUser().getPassword())
                .build();
    }
}
