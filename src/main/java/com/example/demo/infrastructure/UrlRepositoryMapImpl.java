package com.example.demo.infrastructure;

import com.example.demo.domain.ShortenUrl;
import com.example.demo.domain.UrlRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("map")
public class UrlRepositoryMapImpl implements UrlRepository {
    private final Map<String, String> urls = new HashMap<>();

    @Override
    public List<ShortenUrl> findAll() {
        return urls.keySet().stream()
                .map(key ->
                        ShortenUrl.builder()
                                .shortenUrl(key)
                                .originalUrl(urls.get(key))
                                .build())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ShortenUrl> findByShortenUrl(String shortenUrl) {
        String originalUrl = urls.get(shortenUrl);
        if (originalUrl==null) {
            return Optional.empty();
        } else {
            return Optional.of(ShortenUrl.builder()
                    .originalUrl(originalUrl)
                    .shortenUrl(shortenUrl)
                    .build());
        }
    }

    @Override
    public ShortenUrl save(ShortenUrl shortenUrl) {
        urls.put(shortenUrl.getShortenUrl(),shortenUrl.getOriginalUrl());
        return shortenUrl;
    }
}
