package com.example.meetexApi.dto.community;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommunityRequestDTO {
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 500)
    private String description;

    @NotBlank
    @Size(max = 20)
    private String category;

    private String imageUrl;
}
