package com.example.study.controller;

import com.example.study.payload.request.PostRequest;
import com.example.study.payload.response.PostResponse;
import com.example.study.domain.service.PostService;
import com.example.study.validation.NotEmptyGroup;
import com.example.study.validation.PositiveGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public void creatPost(@Validated @RequestBody PostRequest request) {
        postService.createPost(request);
    }

    @PatchMapping("/{postId}")
    public void updatePost(@PathVariable
                           @NotNull(message = "게시글 Id는 필수 항목 입니다.", groups = NotEmptyGroup.class)
                           @Positive(message = "게시글 Id는 양수여야 합니다.", groups = PositiveGroup.class)
                           Integer postId,
                           @Validated @RequestBody PostRequest request) {
        postService.updatePost(request, postId);
    }

    @GetMapping
    public List<PostResponse> getPostList() {
        return postService.getPostList();
    }

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable
                                @NotNull(message = "게시글 Id는 필수 항목 입니다.", groups = NotEmptyGroup.class)
                                @Positive(message = "게시글 Id는 양수여야 합니다.", groups = PositiveGroup.class)
                                Integer postId) {
        return postService.getPost(postId);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable
                           @NotNull(message = "게시글 Id는 필수 항목 입니다.", groups = NotEmptyGroup.class)
                           @Positive(message = "게시글 Id는 양수여야 합니다.", groups = PositiveGroup.class)
                           Integer postId) {
        postService.deletePost(postId);
    }

}
