package com.example.demo.controller;


import com.example.demo.data.UrlData;
import com.example.demo.dto.DecodingUrl;
import com.example.demo.dto.EncodingUrl;
import com.example.demo.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UrlController {
    private final UrlService urlService;
    private final UrlData urlData;

    @GetMapping("/url")
    public Map<String,String> getAllUrls() {
        Map<String,String> datas = new HashMap<>();
        List<String> urls = urlData.getUrls();
        for (int i = 0; i < urls.size(); i++) {
            datas.put(String.valueOf(i),urls.get(i));
        }
        return datas;
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

    @GetMapping("/{encodedUrl}")
    public void redirect(@PathVariable("encodedUrl") final String encodedUrl,
                         HttpServletResponse response) throws IOException {
        response.sendRedirect(urlService.getRedirectUrl(encodedUrl));
    }
}
