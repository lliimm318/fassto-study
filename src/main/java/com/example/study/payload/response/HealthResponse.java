package com.example.study.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class HealthResponse {

    private String Status;
    private LocalDateTime time;

}
