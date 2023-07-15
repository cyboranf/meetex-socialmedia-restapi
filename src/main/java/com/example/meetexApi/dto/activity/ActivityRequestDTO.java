package com.example.meetexApi.dto.activity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class ActivityRequestDTO {
    @NotBlank
    @Size(max = 100)
    private String name;
}
