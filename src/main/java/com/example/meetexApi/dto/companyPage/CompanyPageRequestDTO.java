package com.example.meetexApi.dto.companyPage;

import lombok.Data;

@Data
public class CompanyPageRequestDTO {
    private String name;
    private String description;
    private Long ownerId;
    private String logoUrl;
}
