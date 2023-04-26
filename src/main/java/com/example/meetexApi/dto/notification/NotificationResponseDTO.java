package com.example.meetexApi.dto.notification;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NotificationResponseDTO {
    private Long id;
    private String description;
    private Long fromUserId;
    private Long toUserId;
    private LocalDate date;
}
