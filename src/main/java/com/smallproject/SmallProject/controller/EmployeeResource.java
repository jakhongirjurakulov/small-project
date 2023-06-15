package com.smallproject.SmallProject.controller;

import com.smallproject.SmallProject.dto.EmployeeDto;
import com.smallproject.SmallProject.domain.EmployeeEntity;
import com.smallproject.SmallProject.service.EmployeeService;
import com.smallproject.SmallProject.service.EmployeeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmployeeResource {
    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity getAll() {
        var employees = employeeService.getAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity getOne(@PathVariable Long id, String employeeName, String companyName, String companyAddress) {
        EmployeeDto employee = new EmployeeDto(id, employeeName, companyName, companyAddress);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employee/edit/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody EmployeeEntity newEmployee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeName(newEmployee.getName());
//        employeeDto.setCompanyName(newEmployee.getCompanyName());
//        employeeDto.setCompanyAddress(newEmployee.getCompanyAddress());
//        employeeDto.setCompanyZipCode(newEmployee.getCompanyZipCode());
        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("/employee/delete/{id}")
    public String delete(@PathVariable Long id) throws Exception {
        return employeeService.delete(id);
    }

    @GetMapping("currency-rate")
    public ResponseEntity getAllCurrency() {
        String uri = "https://nbu.uz/uz/exchange-rates/json/";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return ResponseEntity.ok(result);
    }
}
