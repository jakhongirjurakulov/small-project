package com.smallproject.SmallProject.service;

import com.smallproject.SmallProject.dto.EmployeeDto;
import com.smallproject.SmallProject.entity.EmployeeEntity;
import com.smallproject.SmallProject.repository.CompanyRepository;
import com.smallproject.SmallProject.repository.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl {
    private EmployeeRepository employeeRepository;
    private CompanyRepository companyRepository;
    private PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public EmployeeEntity save(EmployeeDto employeeDto) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setCompanyName(employeeDto.getCompanyName());
        employee.setCompanyAddress(employeeDto.getCompanyAddress());
        // encrypt the password using spring security
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        return employee;
    }

    public List<EmployeeDto> getAll() {
        List<EmployeeEntity> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> mapToUserDto(employee))
                .collect(Collectors.toList());
    }

    private EmployeeDto mapToUserDto(EmployeeEntity employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeName(employee.getEmployeeName());
        employeeDto.setCompanyName(employee.getCompanyName());
        employeeDto.setCompanyAddress(employee.getCompanyAddress());
        employee.setCompanyZipCode(employee.getCompanyZipCode());
        return employeeDto;
    }

    public String delete(Long employeeId) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(employeeId);
        return employee + "was deleted";
    }
}
