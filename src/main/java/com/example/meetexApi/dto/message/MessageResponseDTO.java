package com.example.meetexApi.dto.message;

import lombok.Data;


import java.time.LocalDateTime;

@Data
public class MessageResponseDTO {
    private Long id;
    private String text;
    private LocalDateTime sendDate;
    private Long senderId;
    private Long recipientId;
}
