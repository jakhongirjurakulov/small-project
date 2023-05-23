package com.smallproject.SmallProject.repository;

import com.smallproject.SmallProject.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long>{
    CompanyEntity findByName(String companyName);
}
