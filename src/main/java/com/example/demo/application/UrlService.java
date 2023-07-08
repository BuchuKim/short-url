package com.example.demo.application;

import com.example.demo.domain.InvalidUrlException;
import com.example.demo.domain.ShortenUrl;
import com.example.demo.domain.UrlNotFoundException;
import com.example.demo.domain.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;

    @Transactional
    public ShortenUrl shortenUrl(String originalUrl) {
        validateUrl(originalUrl);

        String shortenUrl = ShortenUrl.generateShortenUrl();
        while (urlRepository.findByShortenUrl(shortenUrl).isPresent()) {
            shortenUrl = ShortenUrl.generateShortenUrl();
        }

        return urlRepository.save(
                ShortenUrl.builder()
                .originalUrl(originalUrl)
                .shortenUrl(shortenUrl)
                .build());
    }

    @Transactional
    public String getOriginalUrl(String shortenUrl) {
        ShortenUrl found = urlRepository.findByShortenUrl(shortenUrl)
                .orElseThrow(UrlNotFoundException::new);
        found.addRequestedNumber();
        return found.getOriginalUrl();
    }

    @Transactional(readOnly = true)
    public ShortenUrl getByShortenUrl(String shortenUrl) {
        return urlRepository.findByShortenUrl(shortenUrl)
                .orElseThrow(UrlNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<ShortenUrl> getAllUrls() {
        return urlRepository.findAll();
    }

    private void validateUrl(String url) {
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            throw new InvalidUrlException();
        }
    }
}
