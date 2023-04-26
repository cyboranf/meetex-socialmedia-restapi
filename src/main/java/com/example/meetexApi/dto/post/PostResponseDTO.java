package com.example.meetexApi.dto.post;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PostResponseDTO {
    private Long id;
    private String title;
    private String text;
    private LocalDate sendDate;
    private Long senderId;
    private List<Long> addresseeIds;
    private int reactions;
    private int commentCount;
}
