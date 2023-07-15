package com.example.meetexApi.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CommentRequestDTO {
    @NotBlank
    @Size(max = 150)
    private String text;
    private Long postId;
}
