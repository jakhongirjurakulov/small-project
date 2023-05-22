package com.smallproject.SmallProject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    @NotEmpty
    private String employeeName;
    @NotEmpty(message = "Password should not be empty")
    private String password;
    @NotEmpty
    private String companyName;
    @NotEmpty
    private String companyAddress;
    @NotEmpty
    private String companyZipCode;
}
