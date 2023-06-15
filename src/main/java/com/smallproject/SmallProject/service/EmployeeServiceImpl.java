package com.smallproject.SmallProject.service;

import com.smallproject.SmallProject.dto.EmployeeDto;
import com.smallproject.SmallProject.domain.EmployeeEntity;
import com.smallproject.SmallProject.repository.CompanyRepository;
import com.smallproject.SmallProject.repository.EmployeeRepository;
import jakarta.persistence.NoResultException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private CompanyRepository companyRepository;
    private PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    implement
    public EmployeeEntity save(EmployeeDto employeeDto) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(employeeDto.getEmployeeName());
//        employee.setCompanyName(employeeDto.getCompanyName());
//        employee.setCompanyAddress(employeeDto.getCompanyAddress());
        // encrypt the password using spring security
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        employeeRepository.save(employee);
        return employee;
    }
//    implement
    public List<EmployeeDto> getAll() {
        List<EmployeeEntity> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> mapToUserDto(employee))
                .collect(Collectors.toList());
    }

    public EmployeeDto mapToUserDto(EmployeeEntity employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeName(employee.getName());
//        employeeDto.setCompanyName(employee.getCompanyName());
//        employeeDto.setCompanyAddress(employee.getCompanyAddress());
//        employee.setCompanyZipCode(employee.getCompanyZipCode());
        return employeeDto;
    }
//    implement
    public String delete(Long employeeId) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            employeeRepository.delete(optionalEmployee.get());
        }else {
            throw new NoResultException();
        }
        return optionalEmployee.get().getEmail() + "was deleted";
    }
}
