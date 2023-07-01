package com.example.demo.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository {
    List<ShortenUrl> findAll();
    ShortenUrl findByShortenUrl(String shortenUrl);

    ShortenUrl save(ShortenUrl shortenUrl);
    int getTotalUrlSize();
}
