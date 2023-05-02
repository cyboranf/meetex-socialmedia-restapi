package com.example.meetexApi.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateRequest {
    private String title;
    private String text;
}
