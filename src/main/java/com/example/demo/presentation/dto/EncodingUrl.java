package com.example.demo.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class EncodingUrl {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        @NotBlank
        @NotNull
        private String url;
    }

    @Getter
    @Builder
    public static class Response {
        private String originalUrl;
        private String encodedUrl;
    }
}
