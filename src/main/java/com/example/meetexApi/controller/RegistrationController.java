package com.example.meetexApi.controller;

import com.example.meetexApi.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
}
