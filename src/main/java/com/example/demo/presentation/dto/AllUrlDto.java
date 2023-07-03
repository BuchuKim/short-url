package com.example.demo.presentation.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AllUrlDto {
    private List<UrlRecordDto> urls;
    private int totalSize;

    public static AllUrlDto fromEntity(List<UrlRecordDto> urls) {
        return AllUrlDto.builder()
                .urls(urls)
                .totalSize(urls.size())
                .build();
    }
}
