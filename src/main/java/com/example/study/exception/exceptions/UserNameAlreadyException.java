package com.example.study.exception.exceptions;

import com.example.study.exception.ErrorCode;

public class UserNameAlreadyException extends BaseException {
    public UserNameAlreadyException() {
        super(ErrorCode.USER_NAME_ALREADY);
    }
}
