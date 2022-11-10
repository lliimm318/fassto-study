package com.example.study.payload.request;

import com.example.study.validation.MinMaxGroup;
import com.example.study.validation.NotEmptyGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostRequest {

    @NotBlank(message = "제목은 필수 입력값 입니다.", groups = NotEmptyGroup.class)
    @Max(value = 100, message = "제목은 100자 이하여야 합니당", groups = MinMaxGroup.class)
    private String title;

    @NotBlank(message = "내용은 필수 입력값 입니다.", groups = NotEmptyGroup.class)
    @Max(value = 100, message = "내용은 1000자 이하여야 합니당", groups = MinMaxGroup.class)
    private String content;

}
