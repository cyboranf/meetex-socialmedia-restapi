package com.example.meetexApi.dto.notification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NotificationRequestDTO {
    @NotBlank
    @Size(max = 1000)
    private String description;
    private Long fromUserId;
    private Long toUserId;
}
