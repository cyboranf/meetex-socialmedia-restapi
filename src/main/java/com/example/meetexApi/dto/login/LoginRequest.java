package com.example.meetexApi.dto.login;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;
}
