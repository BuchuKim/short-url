package com.example.demo.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UrlExceptionResponse {
    private final String code;
    private final String message;
}
