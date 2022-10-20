package com.example.study.controller;

import com.example.study.payload.request.PostRequest;
import com.example.study.payload.response.PostResponse;
import com.example.study.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public void updatePost(@PathVariable Integer postId,
                           @Validated @RequestBody PostRequest request) {
        postService.updatePost(request, postId);
    }

    @GetMapping
    public List<PostResponse> getPostList() {
        return postService.getPostList();
    }

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Integer postId) {
        return postService.getPost(postId);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
    }

}
