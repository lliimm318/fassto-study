package com.example.study.exception.exceptions;

import com.example.study.exception.ErrorCode;

public class UserIdAlreadyException extends BaseException {
    public UserIdAlreadyException() {
        super(ErrorCode.USER_ID_ALREADY);
    }
}