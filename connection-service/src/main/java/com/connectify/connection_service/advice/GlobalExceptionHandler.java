package com.connectify.connection_service.advice;

import com.connectify.connection_service.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(BadRequestException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

}
