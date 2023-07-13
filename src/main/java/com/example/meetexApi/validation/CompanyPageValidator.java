package com.example.meetexApi.validation;

import com.example.meetexApi.exception.companyPage.CompanyPageNotFoundException;
import com.example.meetexApi.model.CompanyPage;
import com.example.meetexApi.repository.CompanyPageRepository;
import org.springframework.stereotype.Component;

@Component
public class CompanyPageValidator {
    private final CompanyPageRepository companyPageRepository;

    public CompanyPageValidator(CompanyPageRepository companyPageRepository) {
        this.companyPageRepository = companyPageRepository;
    }

    /**
     * @param id
     * @return CompanyPageNotFoundException or CompanyPage to delete
     */
    public CompanyPage deleteValidation(Long id) {
        return companyPageRepository.findById(id)
                .orElseThrow(() -> new CompanyPageNotFoundException("Can not found company page which you want to delete."));
    }

    /**
     * @param id
     * @return Company Page that user is searching for
     */
    public CompanyPage findByIdValidation(Long id) {
        return companyPageRepository.findById(id)
                .orElseThrow(() -> new CompanyPageNotFoundException("Can not found company page with id = " + id));
    }
}
