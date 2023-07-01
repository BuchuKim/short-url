package com.example.demo.presentation.controller;


import com.example.demo.presentation.dto.DecodingUrl;
import com.example.demo.presentation.dto.EncodingUrl;
import com.example.demo.presentation.dto.ReqNumResponse;
import com.example.demo.application.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UrlController {
    private final UrlService urlService;

    @GetMapping("/url")
    public Map<String,Object> getAllUrls() {
        return urlService.getAllUrls();
    }

    @PostMapping("/url")
    public EncodingUrl.Response encodeUrl(@Valid @RequestBody
                                              final EncodingUrl.Request request) {
        return EncodingUrl.Response.builder()
                .originalUrl(request.getUrl())
                .encodedUrl(urlService.encodeUrl(request.getUrl()))
                .build();
    }

    @GetMapping("/url/{encodedUrl}")
    public DecodingUrl.Response decodeUrl(@PathVariable("encodedUrl")
                                             final String encodedUrl) {
        return DecodingUrl.Response.builder()
                .encodedUrl(encodedUrl)
                .decodedUrl(urlService.decodeUrl(encodedUrl))
                .build();
    }

    @GetMapping("/check/{encodedUrl}")
    public ReqNumResponse getRequestedNumber(@PathVariable("encodedUrl") final String encodedUrl) {
        return ReqNumResponse.builder()
                .encodedUrl(encodedUrl)
                .originalUrl(urlService.decodeUrl(encodedUrl))
                .requestedNumber(urlService.getRequestedNumOfUrl(encodedUrl))
                .build();
    }

    @GetMapping("/{encodedUrl}")
    public RedirectView redirect(@PathVariable("encodedUrl") final String encodedUrl) {
        return new RedirectView(urlService.getRedirectUrl(encodedUrl));
    }
}
