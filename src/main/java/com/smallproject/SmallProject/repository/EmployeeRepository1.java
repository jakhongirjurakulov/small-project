package com.smallproject.SmallProject.repository;

import com.smallproject.SmallProject.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("employeeRepository1")
public interface EmployeeRepository1 extends CrudRepository<EmployeeEntity, String> {
    EmployeeEntity findByEmailIdIgnoreCase(String emailId);
}
