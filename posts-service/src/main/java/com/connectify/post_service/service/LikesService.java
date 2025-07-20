package com.connectify.post_service.service;

import com.connectify.post_service.context.UserContextHolder;
import com.connectify.post_service.entity.PostLike;
import com.connectify.post_service.exception.BadRequestException;
import com.connectify.post_service.exception.ResourceNotFoundException;
import com.connectify.post_service.repository.LikesRepository;
import com.connectify.post_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikesService {

    private final PostRepository postRepository;
    private final LikesRepository likesRepository;

    public void likeAPost(Long postId) {
        Long userId = UserContextHolder.getCurrentUser().getId();
        boolean isExists = postRepository.existsById(postId);
        if(!isExists) throw new ResourceNotFoundException("Post not found with id: " + postId);

        boolean isLiked = likesRepository.existsByPostIdAndUserId(postId, userId);
        if(isLiked) {
            throw new BadRequestException("Cannot like the same post again");
        }

        PostLike postLike = PostLike.builder()
                .postId(postId)
                .userId(userId)
                .build();
        likesRepository.save(postLike);
    }

    public void unlikeAPost(Long postId) {
        Long userId = UserContextHolder.getCurrentUser().getId();
        boolean isExists = postRepository.existsById(postId);
        if(!isExists) throw new ResourceNotFoundException("Post not found with id: " + postId);

        boolean isLiked = likesRepository.existsByPostIdAndUserId(postId, userId);
        if(!isLiked) {
            throw new BadRequestException("Cannot unlike the same post again");
        }

        likesRepository.deleteByPostIdAndUserId(postId, userId);
    }
}
