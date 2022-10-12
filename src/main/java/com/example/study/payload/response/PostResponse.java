package com.example.study.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PostResponse {

    private Integer id;
    private String title;
    private String content;
    private String writer;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
