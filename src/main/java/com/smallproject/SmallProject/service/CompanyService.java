package com.smallproject.SmallProject.service;

import com.smallproject.SmallProject.entity.CompanyEntity;
import com.smallproject.SmallProject.repository.CompanyRepository;

public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyEntity save(CompanyEntity company) {
        return companyRepository.save(company);
    }
}
