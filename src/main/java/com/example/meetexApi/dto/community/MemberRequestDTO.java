package com.example.meetexApi.dto.community;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class MemberRequestDTO {
    @NotNull
    private Long userId;
}
