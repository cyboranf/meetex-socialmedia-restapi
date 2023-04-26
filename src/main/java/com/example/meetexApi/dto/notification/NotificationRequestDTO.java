package com.example.meetexApi.dto.notification;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class NotificationRequestDTO {
    @NotBlank
    @Size(max = 1000)
    private String description;
    private Long fromUserId;
    private Long toUserId;
}
