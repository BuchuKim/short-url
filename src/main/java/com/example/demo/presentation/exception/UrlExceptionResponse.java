package com.example.demo.presentation.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UrlExceptionResponse {
    private String message;
}
