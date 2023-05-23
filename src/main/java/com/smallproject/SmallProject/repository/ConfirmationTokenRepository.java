package com.smallproject.SmallProject.repository;

import com.smallproject.SmallProject.entity.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("confirmationTokenRepository")
public interface ConfirmationTokenRepository extends CrudRepository<EmployeeRepository, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}