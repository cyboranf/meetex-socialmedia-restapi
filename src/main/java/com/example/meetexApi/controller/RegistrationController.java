package com.example.meetexApi.controller;

import com.example.meetexApi.model.User;
import com.example.meetexApi.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String createAnAccount(@Validated @ModelAttribute("user") User user,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            return "This email is already used";
        }
        if (!user.getPassword().equals(user.getMatchingPassword())) {
            return "Passwords are not the same";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);

        return "user";

    }
}
