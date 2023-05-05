package com.example.meetexApi.dto.community;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MemberRequestDTO {
    @NotNull
    private Long userId;
}
