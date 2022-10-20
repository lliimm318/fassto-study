package com.example.study.payload.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class RegisterRequest {

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String password;

}
