package com.example.demo.infrastructure;

import com.example.demo.domain.ShortenUrl;
import com.example.demo.domain.UrlRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("list")
public class UrlRepositoryListImpl implements UrlRepository {
    private final List<ShortenUrl> shortenUrls = new ArrayList<>();

    @Override
    public List<ShortenUrl> findAll() {
        // deep copy
        return new ArrayList<>(shortenUrls);
    }

    @Override
    public Optional<ShortenUrl> findByShortenUrl(String shortenUrl) {
        return shortenUrls.stream()
                .filter(url -> url.getShortenUrl().equals(shortenUrl)).findAny();
    }

    @Override
    public ShortenUrl save(ShortenUrl shortenUrl) {
        shortenUrls.add(shortenUrl);
        return shortenUrl;
    }
}
