package com.example.demo.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;

@Entity
public class ShortenUrl {
    @Id
    @GeneratedValue(generator = "randomLongIdGenerator")
    private Long id;

    private String originalUrl;
    private String shortenUrl;

    @Min(value = 0)
    private long requestedNumber;
}
