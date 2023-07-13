package com.example.meetexApi.service;

import com.example.meetexApi.dto.companyPage.CompanyPageResponseDTO;
import com.example.meetexApi.mapper.CompanyPageMapper;
import com.example.meetexApi.model.CompanyPage;
import com.example.meetexApi.repository.CompanyPageRepository;
import com.example.meetexApi.validation.CompanyPageValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CompanyPageService {
    private final CompanyPageRepository companyPageRepository;
    private final CompanyPageValidator companyPageValidator;
    private final CompanyPageMapper companyPageMapper;

    public CompanyPageService(CompanyPageRepository companyPageRepository, CompanyPageValidator companyPageValidator, CompanyPageMapper companyPageMapper) {
        this.companyPageRepository = companyPageRepository;
        this.companyPageValidator = companyPageValidator;
        this.companyPageMapper = companyPageMapper;
    }

    /**
     * @param id
     */
    public void delete(Long id) {
        companyPageRepository.delete(companyPageValidator.deleteValidation(id));
    }

    /**
     * @param id
     * @return
     */
    public CompanyPageResponseDTO findById(Long id) {
        return companyPageMapper.companyPageToCompanyPageResponseDTO(companyPageValidator.findByIdValidation(id));
    }
}
