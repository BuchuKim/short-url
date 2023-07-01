package com.example.demo.presentation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<UrlExceptionResponse> handleGlobalException(Exception e) {
        log.error("global error : {}", e);
        return ResponseEntity
                .internalServerError()
                .body(new UrlExceptionResponse("알 수 없는 오류가 발생했습니다!"));
    }
}
