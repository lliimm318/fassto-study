package com.example.study.payload.request;

import com.example.study.validation.MinMaxGroup;
import com.example.study.validation.NotEmptyGroup;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Getter
public class RegisterRequest {

    @NotNull(message = "아이디는 필수 입력값 입니다.", groups = NotEmptyGroup.class)
    @Max(value = 50, message = "아이디는 50자 이하여야 합니당", groups = MinMaxGroup.class)
    private String id;

    @NotNull(message = "닉네임은 필수 입력값 입니다.", groups = NotEmptyGroup.class)
    @Max(value = 10, message = "닉네임은 10자 이하여야 합니당", groups = MinMaxGroup.class)
    private String name;

    @NotNull(message = "비밀번호는 필수 입력값 입니다.", groups = NotEmptyGroup.class)
    private String password;

}
