package com.example.meetexApi.dto.user;

import lombok.Data;

@Data
public class FriendRequestDTO {
    private Long senderId;
    private Long receiverId;
}
