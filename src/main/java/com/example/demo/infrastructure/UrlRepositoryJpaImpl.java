package com.example.demo.infrastructure;

import com.example.demo.domain.ShortenUrl;
import com.example.demo.domain.UrlNotFoundException;
import com.example.demo.domain.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public ShortenUrl findByShortenUrl(String shortenUrl) {
        return urlJpaRepository.findByShortenUrl(shortenUrl)
                .orElseThrow(UrlNotFoundException::new);
    }

    @Override
    public ShortenUrl save(ShortenUrl shortenUrl) {
        return urlJpaRepository.save(shortenUrl);
    }

    @Override
    public int getTotalUrlSize() {
        return (int) urlJpaRepository.count();
    }
}
