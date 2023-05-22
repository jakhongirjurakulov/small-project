package com.smallproject.SmallProject.controller;

import com.smallproject.SmallProject.dto.EmployeeDto;
import com.smallproject.SmallProject.entity.Employee;
import com.smallproject.SmallProject.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("{companyId}/employee/create/registration")
    public ResponseEntity create(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        var employee = employeeService.save(employeeDto);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/employees")
    public ResponseEntity getAll() {
        var employees = employeeService.getAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/{companyId}")
    public ResponseEntity get(@PathVariable Long id, String name, String position) {
        EmployeeDto employee = new EmployeeDto(id, name, position);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employee/edit/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody Employee newEmployee) {
        EmployeeDto employee1 = new EmployeeDto(1L, "Javoxir", "Java Dev");
//        employee1.setName(newEmployee.getEmployeeName());
//        return ResponseEntity.ok(employee1);
        return null;
    }

    @DeleteMapping("/employee/delete/{id}")
    public String delete(@PathVariable Long id) {
        return "An employee was deleted";
    }

    @GetMapping("currency-rate")
    public ResponseEntity getAllCurrency() {
        String uri = "https://nbu.uz/uz/exchange-rates/json/";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }
}
