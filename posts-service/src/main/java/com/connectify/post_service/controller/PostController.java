package com.connectify.post_service.controller;

import com.connectify.post_service.context.UserContextHolder;
import com.connectify.post_service.dto.PostCreateRequestDto;
import com.connectify.post_service.dto.PostDto;
import com.connectify.post_service.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto, HttpServletRequest httpServletRequest) {
        PostDto postDto = postService.createPost(postCreateRequestDto);
        return ResponseEntity.ok().body(postDto);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        log.info("User Id is: {}", UserContextHolder.getCurrentUser());
        PostDto postDto = postService.getPostById(postId);
        return ResponseEntity.ok().body(postDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return ResponseEntity.ok().body(posts);
    }

}
