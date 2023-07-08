package com.example.demo.infrastructure;

import com.example.demo.domain.ShortenUrl;
import com.example.demo.domain.UrlRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("map")
public class UrlRepositoryMapImpl implements UrlRepository {
    private final Map<String, ShortenUrl> urls = new HashMap<>();

    @Override
    public List<ShortenUrl> findAll() {
        return (List<ShortenUrl>) urls.values();
    }

    @Override
    public Optional<ShortenUrl> findByShortenUrl(String shortenUrl) {
        return Optional.of(urls.get(shortenUrl));
    }

    @Override
    public ShortenUrl save(ShortenUrl shortenUrl) {
        urls.put(shortenUrl.getShortenUrl(), shortenUrl);
        return shortenUrl;
    }
}
