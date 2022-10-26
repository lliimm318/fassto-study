package com.example.study.domain.service;

import com.example.study.payload.response.HealthResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HealthServiceImpl implements HealthService {

    @Override
    public HealthResponse health() {
        return HealthResponse.builder()
                .Status("server running")
                .time(LocalDateTime.now())
                .build();
    }

}
