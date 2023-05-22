package com.smallproject.SmallProject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employee")
public class Employee {
//    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String employeeName;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String companyName;

    @Column(nullable=false)
    private String companyAddress;

    @Column(nullable=false)
    private String companyZipCode;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="employee_company",
            joinColumns={@JoinColumn(name="employee_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="company_id", referencedColumnName="id")})
    private List<Company> companies = new ArrayList<>();
}
