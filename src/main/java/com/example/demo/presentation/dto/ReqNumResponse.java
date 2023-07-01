package com.example.demo.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReqNumResponse {
    private String encodedUrl;
    private String originalUrl;
    private int requestedNumber;
}
