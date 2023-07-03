package com.example.demo.infrastructure;

import com.example.demo.domain.ShortenUrl;
import com.example.demo.domain.UrlNotFoundException;
import com.example.demo.domain.UrlRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UrlRepositoryImpl implements UrlRepository {
    private final List<ShortenUrl> shortenUrls = new ArrayList<>();

    @Override
    public List<ShortenUrl> findAll() {
        // deep copy
        return new ArrayList<>(shortenUrls);
    }

    @Override
    public ShortenUrl findByShortenUrl(String shortenUrl) {
        int index = ShortenUrl.decodeUrlIndex(shortenUrl);
        if (index < 0 || index >= shortenUrls.size()) throw new UrlNotFoundException();
        return shortenUrls.get(index);
    }

    @Override
    public ShortenUrl save(ShortenUrl shortenUrl) {
        shortenUrls.add(shortenUrl);
        return shortenUrl;
    }

    @Override
    public int getTotalUrlSize() {
        return shortenUrls.size();
    }
}
