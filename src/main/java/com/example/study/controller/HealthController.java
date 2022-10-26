package com.example.study.controller;

import com.example.study.domain.service.HealthService;
import com.example.study.payload.response.HealthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthController {

    private final HealthService healthService;

    @GetMapping("/health")
    public HealthResponse isHealth() {
        return healthService.health();
    }

}
