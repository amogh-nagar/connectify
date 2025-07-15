package com.connectify.post_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private long id;
    private String content;
    private Long userId;
    private LocalDateTime createdAt;
}
