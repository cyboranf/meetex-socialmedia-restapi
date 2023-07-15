package com.example.meetexApi.dto.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageRequestDTO {
    @NotNull
    private Long recipientId;

    @NotBlank
    private String text;
}
