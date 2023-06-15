package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UrlRecordDto {
    private final String originalUrl;
    private final String encodedUrl;
}
