package com.example.demo.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortenUrl {
    @Id
    @GeneratedValue(generator = "randomLongIdGenerator")
    private Long id;

    private String originalUrl;
    private String shortenUrl;

    @Min(value = 0)
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
