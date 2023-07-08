package com.example.demo.repository;

import com.example.demo.domain.ShortenUrl;
import com.example.demo.domain.UrlRepository;
import com.example.demo.infrastructure.UrlJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles(profiles = "jpa")
public class UrlRepositoryTest {
    // JpaRepository "만" 테스트
    @Autowired

    private UrlJpaRepository urlJpaRepository;


    @DisplayName("저장된 ShortenUrl 검색 테스트")
    @Test
    public void saveTest() {
        String original = "https://test.com";
        String shorten = ShortenUrl.generateShortenUrl();

        urlJpaRepository.save(ShortenUrl.builder()
                .originalUrl(original)
                .shortenUrl(shorten)
                .build());

        Assertions.assertNotNull(urlJpaRepository.findByShortenUrl(shorten));
    }

    @DisplayName("shortenUrl 초기값 세팅 테스트")
    @Test
    public void myTest() {
        String original = "https://test.com";
        String shorten = ShortenUrl.generateShortenUrl();

        urlJpaRepository.save(ShortenUrl.builder()
                .originalUrl(original)
                .shortenUrl(shorten)
                .build());

        ShortenUrl found = urlJpaRepository.findByShortenUrl(shorten).orElseThrow();
        Assertions.assertEquals(original,found.getOriginalUrl());
        Assertions.assertEquals(shorten,found.getShortenUrl());
        Assertions.assertEquals(0,found.getRequestedNumber());
    }
}
