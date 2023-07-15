package com.example.meetexApi.seeder;

import com.example.meetexApi.model.CompanyPage;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.CompanyPageRepository;
import com.example.meetexApi.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
@Order(5)
@Profile("seed")
public class CompanyPageSeeder implements DatabaseSeeder {
    private final CompanyPageRepository companyPageRepository;
    private final UserRepository userRepository;

    public CompanyPageSeeder(CompanyPageRepository companyPageRepository, UserRepository userRepository) {
        this.companyPageRepository = companyPageRepository;
        this.userRepository = userRepository;
    }

    /**
     * CompanyPage:
     * "1": {
     * "name": "Sample Company",
     * "description": "This is a sample company page",
     * "logo": "https://example.com/logo.jpg",
     * "website": "https://example.com"
     * }
     */

    @Override
    public void run(String... args) throws Exception {
        if (companyPageRepository.count() == 0) {

            CompanyPage companyPage = new CompanyPage();
            companyPage.setName("Sample Company");
            companyPage.setDescription("This is a sample company page");
            companyPage.setLogo("https://example.com/logo.jpg");
            companyPage.setWebsite("https://example.com");
            companyPage.setCreationDate(new Date());

            companyPageRepository.save(companyPage);
        }
    }
}
