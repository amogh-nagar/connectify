package com.connectify.post_service.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String content) {
        super(content);
    }
}
