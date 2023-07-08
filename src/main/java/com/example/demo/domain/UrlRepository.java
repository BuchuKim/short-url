package com.example.demo.domain;

import java.util.List;
import java.util.Optional;

public interface UrlRepository {
    List<ShortenUrl> findAll();
    Optional<ShortenUrl> findByShortenUrl(String shortenUrl);
    ShortenUrl save(ShortenUrl shortenUrl);
}
