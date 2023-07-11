package com.example.meetexApi.mapper;

import com.example.meetexApi.dto.companyPage.CompanyPageRequestDTO;
import com.example.meetexApi.dto.companyPage.CompanyPageResponseDTO;
import com.example.meetexApi.model.CompanyPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserMapper.class})
public interface CompanyPageMapper {
    CompanyPageMapper INSTANCE = Mappers.getMapper(CompanyPageMapper.class);

    @Mapping(source = "admins.iterator().next()", target = "owner")
    CompanyPageResponseDTO companyPageToCompanyResponseDTO(CompanyPage companyPage);

    @Mapping(source = "ownerId", target = "admins")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    CompanyPage companyPageRequestDTOtoCompanyPage(CompanyPageRequestDTO companyPageRequestDTO);

}
