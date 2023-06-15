package com.example.demo.data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class UrlData {
    // Base62 형식으로 encoding URL
    private final ArrayList<String> urls = new ArrayList<>();

    // requested number
    private final ArrayList<Integer> requestNum = new ArrayList<>();

    private final ArrayList<String> reserved = new ArrayList<>(Arrays.asList("url","check"));

    public int sizeOfUrls() {
        return urls.size();
    }

    // ArrayList url 추가하고 index return
    public int addUrl(String url) {
        urls.add(url);
        requestNum.add(0);
        return urls.size()-1;
    }

    public boolean contains(String url) {
        return urls.contains(url);
    }
    public String getUrlOfIndex(int index) {
        return urls.get(index);
    }
    public void increaseReqNumOfIndex(int index) {
        requestNum.set(index, requestNum.get(index)+1);
    }

    public int getReqNumOfIndex(int index) {
        return requestNum.get(index);
    }

    public boolean isReserved(final String registeringUrl) {
        return reserved.contains(registeringUrl);
    }
}
