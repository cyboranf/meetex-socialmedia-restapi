package com.example.meetexApi.dto.message;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MessageResponseDTO {
    private Long id;
    private String text;
    private LocalDate sendDate;
    private Long senderId;
    private Long addresseeId;
}
