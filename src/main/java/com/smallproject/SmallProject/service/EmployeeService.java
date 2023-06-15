package com.smallproject.SmallProject.service;

import com.smallproject.SmallProject.dto.EmployeeDto;
import com.smallproject.SmallProject.domain.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
    EmployeeEntity save(EmployeeDto employeeDto);
    List<EmployeeDto> getAll();
    EmployeeDto mapToUserDto(EmployeeEntity employee);
    String delete(Long employeeId);
}
