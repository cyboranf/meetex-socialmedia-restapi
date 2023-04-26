package com.example.meetexApi.dto.activity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ActivityRequestDTO {
    @NotBlank
    @Size(max = 100)
    private String name;
}
