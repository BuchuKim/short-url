package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReqNumResponse {
    private String encodedUrl;
    private String originalUrl;
    private int requestedNumber;
}
