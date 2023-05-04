package com.example.meetexApi.dto.friendRequest;

import lombok.Data;

@Data
public class FriendRequestResponseDTO {
    private Long senderId;
    private String senderUsername;
    private String email;
}
