package com.example.study.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "Asia/Seoul")
    private LocalDateTime dateTime;

    private final int status;

    private final String message;

    private final String url;

    ErrorResponse(LocalDateTime dateTime, int status, String message, String url) {
        this.dateTime = dateTime;
        this.status = status;
        this.message = message;
        this.url = url;
    }

}
