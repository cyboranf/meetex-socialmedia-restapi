package com.example.meetexApi.service;

import com.example.meetexApi.model.CompanyPage;
import com.example.meetexApi.repository.CompanyPageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyPageService {
    private final CompanyPageRepository companyPageRepository;

    public CompanyPageService(CompanyPageRepository companyPageRepository) {
        this.companyPageRepository = companyPageRepository;
    }

    public CompanyPage save(CompanyPage companyPage) {
        return companyPageRepository.save(companyPage);
    }

    public void delete(CompanyPage companyPage) {
        companyPageRepository.delete(companyPage);
    }

    public List<CompanyPage> findAll() {
        return companyPageRepository.findAll();
    }

    public Optional<CompanyPage> findById(Long id) {
        return companyPageRepository.findById(id);
    }
}
