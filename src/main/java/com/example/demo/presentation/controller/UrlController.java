package com.example.demo.presentation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class UrlController {
    @RequestMapping("/")
    public String getMainPage() {
        return "view/index.html";
    }
}
