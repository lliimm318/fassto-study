package com.example.study.domain.service;

import com.example.study.entity.Post;
import com.example.study.exception.PostNotFoundException;
import com.example.study.exception.UserNotAccessException;
import com.example.study.payload.request.PostRequest;
import com.example.study.payload.response.PostResponse;
import com.example.study.repository.PostRepository;
import com.example.study.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final AuthenticationFacade facade;

    @Override
    public void createPost(PostRequest request) {
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .writer(facade.getUser().getName())
                .createdAt(LocalDate.now((ZoneId.of("Asia/Seoul"))))
                .build();

        postRepository.save(post);
    }

    @Override
    public void updatePost(PostRequest request, Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        if (!facade.getUser().getName().equals(post.getWriter())) {
            new UserNotAccessException();
        }

        post.updatePost(request.getTitle(),
                        request.getContent(),
                        LocalDate.now(ZoneId.of("Asia/Seoul")));

        postRepository.save(post);
    }

    @Override
    public PostResponse getPost(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        return PostResponse.builder()
                .id(postId)
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getWriter())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    @Override
    public List<PostResponse> getPostList() {
        return postRepository.findAllByOrderByCreatedAt().stream()
                .map(post -> PostResponse.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .writer(post.getWriter())
                        .createdAt(post.getCreatedAt())
                        .updatedAt(post.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        if (!facade.getUser().getName().equals(post.getWriter())) {
            new UserNotAccessException();
        }

        postRepository.delete(post);
    }
}
