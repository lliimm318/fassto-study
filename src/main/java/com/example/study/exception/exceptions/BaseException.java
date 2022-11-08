package com.example.study.exception.exceptions;

import com.example.study.exception.ErrorCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final ErrorCode errorCode;

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode =errorCode;
    }

}
