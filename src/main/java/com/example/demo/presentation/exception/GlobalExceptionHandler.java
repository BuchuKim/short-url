package com.example.demo.presentation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(2)
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UrlExceptionResponse> handleValidationException(MethodArgumentNotValidException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new UrlExceptionResponse("URL 생성에 필요한 데이터가 없습니다!"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UrlExceptionResponse> handleGlobalException(Exception e) {
        log.warn(e.toString());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new UrlExceptionResponse("알 수 없는 오류가 발생했습니다!"));
    }
}
