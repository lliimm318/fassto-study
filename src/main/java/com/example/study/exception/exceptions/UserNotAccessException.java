package com.example.study.exception.exceptions;

import com.example.study.exception.ErrorCode;

public class UserNotAccessException extends BaseException {
    public UserNotAccessException() {
        super(ErrorCode.USER_NOT_ACCESS);
    }
}
