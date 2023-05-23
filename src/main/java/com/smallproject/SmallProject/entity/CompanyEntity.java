package com.smallproject.SmallProject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="company")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable=false, unique=true)
    private String companyName;

    @Column(nullable=false)
    private String companyAddress;

    @Column(nullable=false)
    private String companyZipCode;

    @OneToMany(mappedBy = "")
    private List<EmployeeEntity> employees;
}

