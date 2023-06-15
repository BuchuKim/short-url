package com.example.demo.dto;


import lombok.Builder;
import lombok.Getter;

public class DecodingUrl {
    @Getter
    @Builder
    public static class Response {
        private final String encodedUrl;
        private final String decodedUrl;
    }
}
