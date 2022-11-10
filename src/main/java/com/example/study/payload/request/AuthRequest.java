package com.example.study.payload.request;

import com.example.study.validation.NotEmptyGroup;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class AuthRequest {

    @NotNull(message = "아이디는 필수 입력값 입니다.", groups = NotEmptyGroup.class)
    private String id;

    @NotNull(message = "비밀반호는 필수 입력값 입니다.", groups = NotEmptyGroup.class)
    private String password;

}
