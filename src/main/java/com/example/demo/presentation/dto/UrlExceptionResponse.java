package com.example.demo.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UrlExceptionResponse {
    private final String code;
    private final String message;
}
