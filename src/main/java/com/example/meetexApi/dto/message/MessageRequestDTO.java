package com.example.meetexApi.dto.message;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class MessageRequestDTO {
    @NotNull
    private Long recipientId;

    @NotBlank
    private String text;
}
