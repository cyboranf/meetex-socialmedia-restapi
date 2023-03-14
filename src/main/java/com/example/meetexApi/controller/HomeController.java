package com.example.meetexApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/welcome")
    public String welcomeHome() {
        return "Hello in Meetex virtual world";
    }
}
