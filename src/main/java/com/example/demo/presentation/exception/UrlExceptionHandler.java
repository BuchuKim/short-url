package com.example.demo.presentation.exception;

import com.example.demo.presentation.dto.UrlExceptionResponse;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UrlExceptionHandler {
    @ExceptionHandler(UrlException.class)
    public ResponseEntity<UrlExceptionResponse> handleUrlException(UrlException e) {
        UrlExceptionResponse response = UrlExceptionResponse.builder()
                .code(String.valueOf(e.getUrlExceptionCode()))
                .message(e.getMessage()).build();
        return ResponseEntity.status(e.getStatusCode())
                .body(response);
    }

    @ExceptionHandler({ValidationException.class, BindException.class})
    public ResponseEntity<UrlExceptionResponse> handleValidationException(Exception e) {
        UrlExceptionResponse response = UrlExceptionResponse.builder()
                .code(String.valueOf(UrlExceptionCode.INVALID_DATA))
                .message(UrlExceptionCode.INVALID_DATA.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UrlExceptionResponse> handleRemainException(Exception e) {
        UrlExceptionResponse response = UrlExceptionResponse.builder()
                .code(String.valueOf(UrlExceptionCode.INTERNAL_ERROR))
                .message(UrlExceptionCode.INTERNAL_ERROR.getMessage()).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
