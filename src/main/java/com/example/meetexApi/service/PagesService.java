package com.example.meetexApi.service;

import com.example.meetexApi.model.Pages;
import com.example.meetexApi.repository.PagesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PagesService {
    private final PagesRepository pagesRepository;

    public PagesService(PagesRepository pagesRepository) {
        this.pagesRepository = pagesRepository;
    }

    public Pages save(Pages pages) {
        return pagesRepository.save(pages);
    }

    public void delete(Pages pages) {
        pagesRepository.delete(pages);
    }

    public List<Pages> findAll() {
        return pagesRepository.findAll();
    }
}
