package com.example.demo.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Random;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ShortenUrl {
    private String originalUrl;
    @Id
    private String shortenUrl;

    private long requestedNumber;

    private static final String TABLE =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int STRING_LENGTH = 7;

    public static String encodeByIndex(int index) {
        StringBuilder res = new StringBuilder();
        do {
            res.append(TABLE.charAt(index % 62));
            index /= 62;
        } while (index % 62 > 0);

        return res.toString();
    }

    public static String generateShortenUrl() {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < STRING_LENGTH; i++) {
            int randomIndex = random.nextInt(TABLE.length());
            res.append(TABLE.charAt(randomIndex));
        }

        return res.toString();
    }

    public void addRequestedNumber() {
        requestedNumber += 1;
    }
}
