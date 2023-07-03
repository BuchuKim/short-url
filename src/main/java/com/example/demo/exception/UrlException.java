package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UrlException extends RuntimeException{
    private final UrlExceptionCode urlExceptionCode;
    private final HttpStatus statusCode;

    public UrlException(UrlExceptionCode code) {
        super(code.getMessage());
        this.urlExceptionCode = code;
        this.statusCode = code.getStatus();
    }
}
