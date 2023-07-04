package com.example.demo.infrastructure;

import com.example.demo.domain.ShortenUrl;
import com.example.demo.domain.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("jpa")
@RequiredArgsConstructor
public class UrlRepositoryJpaImpl implements UrlRepository {
    private final UrlJpaRepository urlJpaRepository;

    @Override
    public List<ShortenUrl> findAll() {
        return urlJpaRepository.findAll();
    }

    @Override
    public Optional<ShortenUrl> findByShortenUrl(String shortenUrl) {
        return urlJpaRepository.findByShortenUrl(shortenUrl);
    }

    @Override
    public ShortenUrl save(ShortenUrl shortenUrl) {
        return urlJpaRepository.save(shortenUrl);
    }
}
