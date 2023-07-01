package com.example.demo.application;

import com.example.demo.domain.InvalidUrlException;
import com.example.demo.domain.ShortenUrl;
import com.example.demo.infrastructure.UrlRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepositoryImpl urlRepository;

    @Transactional
    public ShortenUrl shortenUrl(String originalUrl) {
        validateUrl(originalUrl);

        // index 값이 key 이기 때문에... 먼저 size 받아오기
        int index = urlRepository.getTotalUrlSize();
        String shortenUrl = ShortenUrl.encodeByIndex(index);

        return urlRepository.save(
                ShortenUrl.builder()
                .originalUrl(originalUrl)
                .shortenUrl(shortenUrl)
                .build());
    }

    @Transactional(readOnly = true)
    public String getOriginalUrl(String shortenUrl) {
        ShortenUrl found = urlRepository.findByShortenUrl(shortenUrl);
        return found.getOriginalUrl();
    }

    private void validateUrl(String url) {
        try {
            new URI(url);
        } catch (URISyntaxException e) {
            throw new InvalidUrlException();
        }
    }
}
