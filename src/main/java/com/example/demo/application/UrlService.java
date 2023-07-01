package com.example.demo.application;

import com.example.demo.infrastructure.UrlData;
import com.example.demo.presentation.dto.UrlRecordDto;
import com.example.demo.presentation.exception.UrlException;
import com.example.demo.presentation.exception.UrlExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlService {
    private final UrlData urlData;

    public Map<String,Object> getAllUrls() {
        Map<String,Object> datas = new HashMap<>();
        datas.put("size",urlData.sizeOfUrls());

        List<UrlRecordDto> urls = new ArrayList<>(urlData.sizeOfUrls());
        for (int i=0; i<urlData.sizeOfUrls(); i++) {
            urls.add(UrlRecordDto.builder()
                    .originalUrl(urlData.getUrlOfIndex(i))
                    .encodedUrl(encodeBase62(i))
                    .build());
        }
        datas.put("urls",urls);

        return datas;
    }

    // url 받아서 encode -> 결과 response
    public String encodeUrl(final String url) {
        validateRegisteringUrl(url);
        return encodeBase62(urlData.addUrl(url));
    }

    // encoded 문자열 받아서 decode
    public String decodeUrl(final String encodedUrl) {
        int decodedIndex = decodeBase62(encodedUrl);
        validateIndex(decodedIndex);
        return urlData.getUrlOfIndex(decodedIndex);
    }

    public String getRedirectUrl(final String encodedUrl) {
        int decodedIndex = decodeBase62(encodedUrl);
        validateIndex(decodedIndex);
        urlData.increaseReqNumOfIndex(decodedIndex);
        return "http://" + decodeUrl(encodedUrl);
    }

    public int getRequestedNumOfUrl(final String encodedUrl) {
        int decodedIndex = decodeBase62(encodedUrl);
        validateIndex(decodedIndex);
        return urlData.getReqNumOfIndex(decodedIndex);
    }
    private static final String TABLE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    // int index -> String encodedValue
    private String encodeBase62(int index) {
        StringBuilder res = new StringBuilder();
        do {
            res.append(TABLE.charAt(index % 62));
            index /= 62;
        } while (index % 62 > 0);

        return res.toString();
    }

    // String encodedValue -> int decodedIndex
    private int decodeBase62(final String encodedUrl) {
        int index = 0;
        int pow = 1;
        for (char s : encodedUrl.toCharArray()) {
            index += TABLE.indexOf(s) * pow;
            pow *= 62;
        }
        return index;
    }

    private void validateRegisteringUrl(final String registeringUrl) {
        if (registeringUrl.isEmpty()) {
            throw new UrlException(UrlExceptionCode.EMPTY_DATA);
        }
        if (!registeringUrl.contains(".")
                || urlData.isReserved(registeringUrl)) {
            throw new UrlException(UrlExceptionCode.INVALID_URL);
        }
        if (urlData.contains(registeringUrl)) {
            throw new UrlException(UrlExceptionCode.ALREADY_EXISTS);
        }
    }

    // decoded index url data 있는지 확인
    private void validateIndex(final int index) {
        if (index < 0 || index >= urlData.sizeOfUrls()) {
            throw new UrlException(UrlExceptionCode.NOT_FOUND_URL);
        }
    }
}
