package com.example.demo.presentation.dto;

import com.example.demo.domain.ShortenUrl;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AllUrlDto {
    private List<ShortenUrl> urls;
    private int totalSize;

    public static AllUrlDto fromEntity(List<ShortenUrl> urls) {
        return AllUrlDto.builder()
                .urls(urls)
                .totalSize(urls.size())
                .build();
    }
}
