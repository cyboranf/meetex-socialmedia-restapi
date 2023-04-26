package com.example.meetexApi.dto.message;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class MessageRequestDTO {
    @NotBlank
    @Size(max = 1000)
    private String text;
    private Long addresseeId;
}
