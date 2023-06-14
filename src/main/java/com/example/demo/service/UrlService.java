package com.example.demo.service;

import com.example.demo.data.UrlData;
import com.example.demo.exception.UrlException;
import com.example.demo.exception.UrlExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlService {
    private final UrlData urlData;

    // url 받아서 encode -> 결과 response
    public String encodeUrl(final String url) {
        validateUrl(url);
        urlData.getUrls().add(url);
        return encodeBase62(urlData.getUrls().size()-1);
    }

    // encoded 문자열 받아서 decode
    public String decodeUrl(final String encodedUrl) {
        int decodedIndex = decodeBase62(encodedUrl);
        validateIndex(decodedIndex);
        return urlData.getUrls().get(decodedIndex);
    }

    public String getRedirectUrl(final String encodedUrl) {
        return "http://" + decodeUrl(encodedUrl);
    }

    private static final String TABLE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private String encodeBase62(int index) {
        StringBuilder res = new StringBuilder();
        do {
            res.append(TABLE.charAt(index % 62));
            index /= 62;
        } while (index % 62 > 0);

        return res.toString();
    }

    private int decodeBase62(final String encodedUrl) {
        int index = 0;
        int pow = 1;
        for (char s : encodedUrl.toCharArray()) {
            index += TABLE.indexOf(s) * pow;
            pow *= 62;
        }
        return index;
    }

    private void validateUrl(final String url) {
        if (url.isEmpty()) {
            throw new UrlException(UrlExceptionCode.EMPTY_DATA);
        }
        if (!url.contains(".")) {
            throw new UrlException(UrlExceptionCode.INVALID_URL);
        }
        if (urlData.getUrls().contains(url)) {
            throw new UrlException(UrlExceptionCode.ALREADY_EXISTS);
        }
    }

    private void validateIndex(final int index) {
        if (index < 0 || index >= urlData.getUrls().size()) {
            throw new UrlException(UrlExceptionCode.NOT_FOUND_URL);
        }
    }
}
