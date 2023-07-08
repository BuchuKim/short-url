package com.example.demo.presentation.dto;

import com.example.demo.domain.ShortenUrl;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CreateShortenUrlDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull
        private String originalUrl;
    }

    @Getter
    @Builder
    public static class Response {
        private String originalUrl;
        private String shortenUrl;

        public static CreateShortenUrlDto.Response fromEntity(ShortenUrl shortenUrl) {
            return Response.builder()
                    .originalUrl(shortenUrl.getOriginalUrl())
                    .shortenUrl(shortenUrl.getShortenUrl())
                    .build();
        }
    }
}
