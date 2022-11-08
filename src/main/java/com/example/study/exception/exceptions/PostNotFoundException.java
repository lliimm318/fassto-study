package com.example.study.exception.exceptions;

import com.example.study.exception.ErrorCode;

public class PostNotFoundException extends BaseException {
    public PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND);
    }
}
