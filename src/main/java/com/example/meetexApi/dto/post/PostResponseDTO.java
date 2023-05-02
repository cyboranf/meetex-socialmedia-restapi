package com.example.meetexApi.dto.post;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponseDTO {
    private Long id;
    private String title;
    private String text;
    private LocalDateTime sendDate;
    private Long senderId;
    private List<Long> addresseeIds;
    private int reactions;
    private int commentCount;
}
