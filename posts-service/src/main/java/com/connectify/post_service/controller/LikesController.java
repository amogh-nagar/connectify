package com.connectify.post_service.controller;

import com.connectify.post_service.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {

    private LikesService likesService;

    @PostMapping("/{postId}")
    public ResponseEntity<?> likeAPost(@PathVariable Long postId) {
        likesService.likeAPost(postId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> unlikeAPost(@PathVariable Long postId) {
        likesService.unlikeAPost(postId);
        return ResponseEntity.noContent().build();
    }

}
