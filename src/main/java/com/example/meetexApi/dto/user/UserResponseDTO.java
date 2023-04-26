package com.example.meetexApi.dto.user;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String email;
    private String userName;
    private String lastName;
    private int friendsCount;
    private int msgCount;
    private int notCount;
    private boolean active;
}
