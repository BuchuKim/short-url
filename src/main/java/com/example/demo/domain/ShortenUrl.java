package com.example.demo.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    public static String encodeByIndex(int index) {
        StringBuilder res = new StringBuilder();
        do {
            res.append(TABLE.charAt(index % 62));
            index /= 62;
        } while (index % 62 > 0);

        return res.toString();
    }

    public static int decodeUrlIndex(String shortenUrl) {
        int index = 0;
        int pow = 1;
        for (char s : shortenUrl.toCharArray()) {
            index += TABLE.indexOf(s) * pow;
            pow *= 62;
        }
        return index;
    }

    public void addRequestedNumber() {
        requestedNumber += 1;
    }
}
