package com.example.meetexApi.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CommentRequestDTO {
    @NotBlank
    @Size(max = 1000)
    private String text;
    private Long postId;
}
