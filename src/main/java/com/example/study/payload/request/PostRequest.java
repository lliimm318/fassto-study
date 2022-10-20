package com.example.study.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

}
