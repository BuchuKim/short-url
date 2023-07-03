package com.example.demo.presentation.exception;

import com.example.demo.domain.InvalidUrlException;
import com.example.demo.domain.UrlNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
public class UrlExceptionHandler {
    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<UrlExceptionResponse> handleUrlNotFoundException(UrlNotFoundException nf) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new UrlExceptionResponse("해당하는 URL은 존재하지 않습니다."));
    }

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<UrlExceptionResponse> handleInvalidUrlException(InvalidUrlException iu) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new UrlExceptionResponse("잘못된 URL 형식입니다."));
    }
}
