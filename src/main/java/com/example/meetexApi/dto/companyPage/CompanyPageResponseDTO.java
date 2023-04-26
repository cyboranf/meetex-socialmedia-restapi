package com.example.meetexApi.dto.companyPage;


import com.example.meetexApi.dto.user.UserResponseDTO;
import lombok.Data;

@Data
public class CompanyPageResponseDTO {
    private Long id;
    private String name;
    private String description;
    private UserResponseDTO owner;
    private String logoUrl;
}