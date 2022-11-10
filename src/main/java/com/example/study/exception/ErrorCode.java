package com.example.study.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(401, "invalid token"),
    USER_NOT_ACCESS(403, "user not access"),
    POST_NOT_FOUND(404,"post not found"),
    USER_NOT_FOUND(404,"user not found"),
    USER_ID_ALREADY(409, "user id already"),
    USER_NAME_ALREADY(409, "user name already");

    private final int status;

    private final String message;

}
