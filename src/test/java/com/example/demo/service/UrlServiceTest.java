package com.example.demo.service;

import com.example.demo.application.UrlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UrlServiceTest {
    @Autowired
    private UrlService urlService;

    @DisplayName("단축된 URL이 7자리가 맞는지 확인")
    @Test
    public void shortenUrlLengthTest() {
        String testUrl = "http://test.com";
        Assertions.assertEquals(7,
                urlService.shortenUrl(testUrl).getShortenUrl().length());
    }

    @DisplayName("저장한 shortenUrl을 다시 불러왔을 때 같은지 확인")
    @Test
    public void testSavedUrl() {
        String testUrl = "http://test.com";
        String savedShortenUrl = urlService.shortenUrl(testUrl).getShortenUrl();

        Assertions.assertEquals(testUrl, urlService.getOriginalUrl(savedShortenUrl));
    }
}
