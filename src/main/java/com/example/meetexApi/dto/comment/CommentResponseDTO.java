package com.example.meetexApi.dto.comment;

import lombok.Data;


import java.time.LocalDateTime;

@Data
public class CommentResponseDTO {
    private Long id;
    private String text;
    private LocalDateTime sendDate;
    private Long senderId;
    private Long postId;
}
