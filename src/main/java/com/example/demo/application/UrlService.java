package com.example.demo.application;

import com.example.demo.domain.InvalidUrlException;
import com.example.demo.domain.ShortenUrl;
import com.example.demo.infrastructure.UrlRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepositoryImpl urlRepository;

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

    public String getOriginalUrl(String shortenUrl) {
        ShortenUrl found = urlRepository.findByShortenUrl(shortenUrl);
        found.addRequestedNumber();
        return found.getOriginalUrl();
    }

    private void validateUrl(String url) {
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            throw new InvalidUrlException();
        }
    }
}
