package com.smallproject.SmallProject.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    private String email;

    @Column(nullable=false)
    private String password;
    private String activationCode;
    private boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn
    private CompanyEntity company;
}
