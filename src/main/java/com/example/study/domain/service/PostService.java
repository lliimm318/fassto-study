package com.example.study.domain.service;

import com.example.study.payload.request.PostRequest;
import com.example.study.payload.response.PostResponse;

import java.util.List;

public interface PostService {

    void createPost(PostRequest request);

    void updatePost(PostRequest request, Integer postId);

    PostResponse getPost(Integer postId);

    List<PostResponse> getPostList();

    void deletePost(Integer postId);

}
