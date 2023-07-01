package com.example.demo.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository {
    Optional<ShortenUrl> findByOriginalUrl(String originalUrl);
    Optional<ShortenUrl> findByShortenUrl(String shortenUrl);
}
