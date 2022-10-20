package com.example.study.security.auth;

import com.example.study.entity.User;
import com.example.study.exception.UserNotFoundException;
import com.example.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade {

    private final UserRepository userRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getUserId() {
        return this.getAuthentication().getName();
    }

    public User getUser() {
        return userRepository.findById(getUserId())
                .orElseThrow(UserNotFoundException::new);
    }

}
