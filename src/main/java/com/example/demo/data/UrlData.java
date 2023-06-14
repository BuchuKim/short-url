package com.example.demo.data;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Getter
public class UrlData {
    // Base62 형식으로 encoding URL
    private final ArrayList<String> urls = new ArrayList<>();

    // request number 담기 (Optional)
    private final ArrayList<Integer> requestNum = new ArrayList<>();
}
