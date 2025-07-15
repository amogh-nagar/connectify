package com.connectify.post_service.repository;

import com.connectify.post_service.entity.Post;
import com.connectify.post_service.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<PostLike> findByUserId(Long userId);
}
