package com.smallproject.SmallProject.controller;

import com.smallproject.SmallProject.dto.Employee;
import com.smallproject.SmallProject.entity.EmployeeEntity;
import com.smallproject.SmallProject.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("v1/employee")
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity getAll() {
        Employee employee1 = new Employee(1L, "Jakhongir1", "Content-Manager1");
        Employee employee2 = new Employee(2L, "Jakhongir2", "Content-Manager2");
        Employee employee3 = new Employee(3L, "Jakhongir3", "Content-Manager3");

        List<Employee> employees = new ArrayList<>();
        Collections.addAll(employees, employee1, employee2, employee3);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity get(@PathVariable Long id, String name, String position) {
        Employee employee = new Employee(id, name, position);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/employee/create")
    public ResponseEntity create(@RequestBody EmployeeEntity employee) {
        EmployeeEntity createEmployee = employeeService.save(employee);
        return ResponseEntity.ok(createEmployee);
    }

    @PutMapping("/employee/edit/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody EmployeeEntity newEmployee) {
        Employee employee1 = new Employee(1L, "Javoxir", "Java Dev");
        employee1.setName(newEmployee.getEmployeeName());
        employee1.setName(newEmployee.getEmployeePosition());
        return ResponseEntity.ok(employee1);
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
}
