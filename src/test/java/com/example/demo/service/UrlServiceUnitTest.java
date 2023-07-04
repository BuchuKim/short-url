package com.example.demo.service;

import com.example.demo.application.UrlService;
import com.example.demo.domain.InvalidUrlException;
import com.example.demo.domain.UrlNotFoundException;
import com.example.demo.domain.UrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UrlServiceUnitTest {
    @InjectMocks
    private UrlService urlService;

    @Mock
    private UrlRepository urlRepository;

    @DisplayName("형식에 맞지 않는 URL 요청일 경우 InvalidUrlException")
    @Test
    public void requestWithInvalidUrl() {
        String invalidUrl = "test.invalid";

        Assertions.assertThrows(InvalidUrlException.class,() -> {
            urlService.shortenUrl(invalidUrl);
        });
    }

    @DisplayName("존재하지 않는 URL 조회 요청일 경우 UrlNotFoundException")
    @Test
    public void requestWithNotExistUrl() {
        Mockito.when(urlRepository.findByShortenUrl(Mockito.anyString()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(UrlNotFoundException.class,() -> {
            urlService.getByShortenUrl("http://test.com");
        });
    }
}
