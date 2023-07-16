package com.example.meetexApi.service;

import com.example.meetexApi.dto.companyPage.CompanyPageResponseDTO;
import com.example.meetexApi.mapper.CompanyPageMapper;
import com.example.meetexApi.model.CompanyPage;
import com.example.meetexApi.repository.CompanyPageRepository;
import com.example.meetexApi.service.CompanyPageService;
import com.example.meetexApi.validation.CompanyPageValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CompanyPageServiceTest {

    @Mock
    private CompanyPageRepository companyPageRepository;

    @Mock
    private CompanyPageValidator companyPageValidator;

    @Mock
    private CompanyPageMapper companyPageMapper;

    @InjectMocks
    private CompanyPageService companyPageService;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void deleteCompanyPageTest() {
        Long id = 1L;
        CompanyPage companyPage = new CompanyPage();

        when(companyPageValidator.deleteValidation(id)).thenReturn(companyPage);
        doNothing().when(companyPageRepository).delete(companyPage);

        companyPageService.delete(id);

        verify(companyPageRepository, times(1)).delete(companyPage);
    }

    @Test
    public void findCompanyPageByIdTest() {
        Long id = 1L;
        CompanyPage companyPage = new CompanyPage();
        CompanyPageResponseDTO responseDTO = new CompanyPageResponseDTO();

        when(companyPageValidator.findByIdValidation(id)).thenReturn(companyPage);
        when(companyPageMapper.companyPageToCompanyPageResponseDTO(companyPage)).thenReturn(responseDTO);

        CompanyPageResponseDTO result = companyPageService.findById(id);

        assertEquals(responseDTO, result);
    }
}
