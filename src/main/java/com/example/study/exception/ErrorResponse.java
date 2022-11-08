package com.example.study.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int status;
    private final String message;

    ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
