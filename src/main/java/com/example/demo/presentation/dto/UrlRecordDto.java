package com.example.demo.presentation.dto;

import com.example.demo.domain.ShortenUrl;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UrlRecordDto {
    private final String originalUrl;
    private final String shortenUrl;
    private final long requestedNumber;

    public static UrlRecordDto fromEntity(ShortenUrl shortenUrl) {
        return UrlRecordDto.builder()
                .originalUrl(shortenUrl.getOriginalUrl())
                .shortenUrl(shortenUrl.getShortenUrl())
                .requestedNumber(shortenUrl.getRequestedNumber())
                .build();
    }
}
