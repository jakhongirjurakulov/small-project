package com.smallproject.SmallProject.repository;

import com.smallproject.SmallProject.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long>{
    Company findByName(String companyName);
}
