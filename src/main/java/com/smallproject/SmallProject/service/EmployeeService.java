package com.smallproject.SmallProject.service;

import com.smallproject.SmallProject.dto.EmployeeDto;
import com.smallproject.SmallProject.entity.Employee;
import com.smallproject.SmallProject.repository.CompanyRepository;
import com.smallproject.SmallProject.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private CompanyRepository companyRepository;
    private PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Employee save(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setCompanyName(employeeDto.getCompanyName());
        employee.setCompanyAddress(employeeDto.getCompanyAddress());
        // encrypt the password using spring security
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        return employee;
    }

    public List<EmployeeDto> getAll() {
        List<Employee> users = employeeRepository.findAll();
        return users.stream()
                .map(employee -> mapToUserDto(employee))
                .collect(Collectors.toList());
    }

    private EmployeeDto mapToUserDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeName(employee.getEmployeeName());
        employeeDto.setCompanyName(employee.getCompanyName());
        employeeDto.setCompanyAddress(employee.getCompanyAddress());
        employee.setCompanyZipCode(employee.getCompanyZipCode());
        return employeeDto;
    }
}
