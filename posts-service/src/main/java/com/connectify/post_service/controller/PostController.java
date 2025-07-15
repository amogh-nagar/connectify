package com.connectify.post_service.controller;

import com.connectify.post_service.dto.PostCreateRequestDto;
import com.connectify.post_service.dto.PostDto;
import com.connectify.post_service.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto, HttpServletRequest httpServletRequest) {
        PostDto postDto = postService.createPost(postCreateRequestDto, 1L);
        return ResponseEntity.ok().body(postDto);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        PostDto postDto = postService.getPostById(postId);
        return ResponseEntity.ok().body(postDto);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<PostDto>> getAllPostsForAUser(@PathVariable Long userId) {
        List<PostDto> posts = postService.getAllPostsForAUser(userId);
        return ResponseEntity.ok().body(posts);
    }

}
