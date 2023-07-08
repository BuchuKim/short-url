package com.example.demo.presentation.controller;


import com.example.demo.application.UrlService;
import com.example.demo.domain.ShortenUrl;
import com.example.demo.presentation.dto.AllUrlDto;
import com.example.demo.presentation.dto.CreateShortenUrlDto;
import com.example.demo.presentation.dto.UrlRecordDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UrlRestController {
    private final UrlService urlService;

    @GetMapping("/url")
    public ResponseEntity<AllUrlDto> getAllUrls() {
        List<UrlRecordDto> urls = urlService.getAllUrls().stream()
                .map(UrlRecordDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(
                AllUrlDto.fromEntity(urls));
    }

    @PostMapping("/url")
    public ResponseEntity<CreateShortenUrlDto.Response> shortenUrl(@Valid @RequestBody
                                                 CreateShortenUrlDto.Request request) {
        String originalUrl = request.getOriginalUrl();
        return ResponseEntity.ok().body(
                CreateShortenUrlDto.Response.fromEntity(
                urlService.shortenUrl(originalUrl)));
    }

    @GetMapping("/url/{shortenUrl}")
    public ResponseEntity<UrlRecordDto> getUrlRecord(@PathVariable("shortenUrl")
                                             String shortenUrl) {
        ShortenUrl found = urlService.getByShortenUrl(shortenUrl);
        return ResponseEntity.ok().body(
                UrlRecordDto.fromEntity(found));
    }

    @GetMapping("/{shortenUrl}")
    public ResponseEntity<?> redirect(@PathVariable(value = "shortenUrl")
                                     String shortenUrl) throws URISyntaxException {
        log.info("리다이렉트 요청 : {}",shortenUrl);
        String originalUrl = urlService.getOriginalUrl(shortenUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(originalUrl));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
