package com.example.meetexApi.seeder;

import com.example.meetexApi.model.CompanyPage;
import com.example.meetexApi.repository.CompanyPageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CompanyPageSeederTest {

    @Mock
    private CompanyPageRepository companyPageRepository;

    @InjectMocks
    private CompanyPageSeeder companyPageSeeder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void run_shouldCreateCompanyPage_whenCompanyPageRepositoryIsEmpty() throws Exception {
        when(companyPageRepository.count()).thenReturn(0L);

        companyPageSeeder.run();

        verify(companyPageRepository, times(1)).save(any(CompanyPage.class));
    }

    @Test
    public void run_shouldNotCreateCompanyPage_whenCompanyPageRepositoryIsNotEmpty() throws Exception {
        when(companyPageRepository.count()).thenReturn(1L);

        companyPageSeeder.run();

        verify(companyPageRepository, times(0)).save(any(CompanyPage.class));
    }
}
