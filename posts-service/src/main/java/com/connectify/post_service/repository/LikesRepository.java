package com.connectify.post_service.repository;

import com.connectify.post_service.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface LikesRepository extends JpaRepository<PostLike, Long> {
    boolean existsByPostIdAndUserId(Long userId, Long postId);

    @Transactional
    void deleteByPostIdAndUserId(Long postId, Long userId);
}
