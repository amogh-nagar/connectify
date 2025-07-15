package com.connectify.post_service.service;

import com.connectify.post_service.dto.PostCreateRequestDto;
import com.connectify.post_service.dto.PostDto;
import com.connectify.post_service.entity.Post;
import com.connectify.post_service.entity.PostLike;
import com.connectify.post_service.exception.ResourceNotFoundException;
import com.connectify.post_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + postId));
        return modelMapper.map(post, PostDto.class);
    }

    public List<PostDto> getAllPostsForAUser(Long userId) {
        List<PostLike> postLikes = postRepository.findByUserId(userId);
        return postLikes.stream().map(postLike -> modelMapper.map(postLike, PostDto.class)).collect(Collectors.toList());
    }
}
