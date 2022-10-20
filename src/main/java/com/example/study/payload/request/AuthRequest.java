package com.example.study.payload.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class AuthRequest {

    @NotNull
    private String id;

    @NotNull
    private String password;

}
