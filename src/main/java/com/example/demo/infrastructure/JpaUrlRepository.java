package com.example.demo.infrastructure;

import com.example.demo.domain.ShortenUrl;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("jpa")
public interface JpaUrlRepository extends org.springframework.data.jpa.repository.JpaRepository<ShortenUrl, String> {
    Optional<ShortenUrl> findByShortenUrl(String shortenUrl);
}
