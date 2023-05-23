package com.smallproject.SmallProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="confirmation_token")
public class ConfirmationToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenId;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = EmployeeEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "employee_id")
    private EmployeeEntity employee;

    private Date expiryDate;
//
//    private Date calculateExpiryDate(int expiryTimeInMinutes) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Timestamp(cal.getTime()));
//        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
//        return new Date(cal.getTime().getTime());
//    }

    public ConfirmationToken(EmployeeEntity employeeEntity) {
        this.employee = employeeEntity;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
}
