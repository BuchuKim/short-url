package com.example.demo.domain;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository {
    List<ShortenUrl> findAll();
    ShortenUrl findByShortenUrl(String shortenUrl);

    /**
     * save ShortenUrl record
     * @param shortenUrl entity object to be stored
     */
    ShortenUrl save(ShortenUrl shortenUrl);
    int getTotalUrlSize();
}
