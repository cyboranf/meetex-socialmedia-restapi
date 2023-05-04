package com.example.meetexApi.dto.community;

import lombok.Data;

@Data
public class CommunityResponseDTO {
    private long id;
    private String name;
    private String description;
    private String category;
    private Long creatorId;
    private String imageUrl;
}
