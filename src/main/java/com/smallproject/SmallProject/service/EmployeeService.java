package com.smallproject.SmallProject.service;

import com.smallproject.SmallProject.dto.Employee;
import com.smallproject.SmallProject.entity.EmployeeEntity;
import com.smallproject.SmallProject.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity save(EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }


}
