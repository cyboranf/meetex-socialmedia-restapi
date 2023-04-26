package com.example.meetexApi.dto.community;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CommunityRequestDTO {
    @NotBlank
    @Size(max = 100)
    private String name;
}
