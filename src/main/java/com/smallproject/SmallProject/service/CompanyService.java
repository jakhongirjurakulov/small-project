package com.smallproject.SmallProject.service;

import com.smallproject.SmallProject.entity.Company;
import com.smallproject.SmallProject.repository.CompanyRepository;

public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }
}
