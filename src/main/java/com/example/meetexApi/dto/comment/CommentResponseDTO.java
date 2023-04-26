package com.example.meetexApi.dto.comment;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentResponseDTO {
    private Long id;
    private String text;
    private LocalDate sendDate;
    private Long senderId;
    private Long postId;
}
