package com.connectify.user_service.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String content) {
        super(content);
    }
}
