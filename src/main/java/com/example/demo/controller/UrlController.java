package com.example.demo.controller;


import com.example.demo.dto.DecodingUrl;
import com.example.demo.dto.EncodingUrl;
import com.example.demo.dto.ReqNumResponse;
import com.example.demo.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UrlController {
    private final UrlService urlService;

    @GetMapping("/url")
    public Map<String,String> getAllUrls() {
        return urlService.getAllUrls();
    }

    @PostMapping("/url")
    public EncodingUrl.Response encodeUrl(@Valid @RequestBody final EncodingUrl.Request request) {
        return EncodingUrl.Response.builder()
                .encodedUrl(urlService.encodeUrl(request.getUrl()))
                .build();
    }

    @GetMapping("/url/{encodedUrl}")
    public DecodingUrl.Response decodeUrl(@PathVariable("encodedUrl")
                                             final String encodedUrl) {
        return DecodingUrl.Response.builder()
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
    public void redirect(@PathVariable("encodedUrl") final String encodedUrl,
                         HttpServletResponse response) throws IOException {
        response.sendRedirect(urlService.getRedirectUrl(encodedUrl));
    }
}
