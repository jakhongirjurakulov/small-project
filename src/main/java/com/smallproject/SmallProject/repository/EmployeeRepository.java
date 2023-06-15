package com.smallproject.SmallProject.repository;

import com.smallproject.SmallProject.domain.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    EmployeeEntity findByName(String employeeName);
    EmployeeEntity findByEmailIdIgnoreCase(String emailId);
}
