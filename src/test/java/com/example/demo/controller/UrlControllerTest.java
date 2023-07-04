package com.example.demo.controller;

import com.example.demo.application.UrlService;
import com.example.demo.domain.ShortenUrl;
import com.example.demo.presentation.controller.UrlController;
import com.example.demo.presentation.dto.CreateShortenUrlDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UrlController.class)
@ExtendWith(MockitoExtension.class)
public class UrlControllerTest {
    @MockBean
    private UrlService urlService;

    @Autowired // 자동으로 mockMvc 빌드
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("단축 URL 생성 확인")
    @Test
    public void createShortenUrl() throws Exception {
        Mockito.when(urlService.shortenUrl(Mockito.anyString()))
                .thenReturn(ShortenUrl.builder()
                        .originalUrl("http://test.com")
                        .shortenUrl("test123")
                        .build());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/url")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(
                                new CreateShortenUrlDto.Request(Mockito.anyString()))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.shortenUrl").exists());
    }

    @DisplayName("단축 URL 검색 가능")
    @Test
    public void getUrlRecord() throws Exception {
        Mockito.when(urlService.getByShortenUrl(Mockito.anyString()))
                .thenReturn(ShortenUrl.builder()
                        .originalUrl("http://test.com")
                        .shortenUrl("test123")
                        .build());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/url/{shortenUrl}","test123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.originalUrl").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.requestedNumber").exists());

    }

    @DisplayName("리다이렉트 테스트")
    @Test
    public void createWithInvalidRequest() throws Exception {
        Mockito.when(urlService.getOriginalUrl(Mockito.anyString()))
                .thenReturn("http://test.com");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/{shortenUrl}","test123"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().exists("Location"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("null 요청")
    @Test
    public void createRequestWithInvalidBody() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/url")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(new CreateShortenUrlDto.Request())))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                .andDo(MockMvcResultHandlers.print());
    }
}
