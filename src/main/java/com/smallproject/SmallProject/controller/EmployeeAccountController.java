package com.smallproject.SmallProject.controller;

import com.smallproject.SmallProject.entity.ConfirmationToken;
import com.smallproject.SmallProject.entity.EmployeeEntity;
import com.smallproject.SmallProject.repository.ConfirmationTokenRepository;
import com.smallproject.SmallProject.repository.EmployeeRepository1;
import com.smallproject.SmallProject.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeAccountController {
    @Autowired
    private EmployeeRepository1 employeeRepository1;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody EmployeeEntity employeeEntity) {
        EmployeeEntity existingEmployee = employeeRepository1.findByEmailIdIgnoreCase(employeeEntity.getEmailId());

        if (existingEmployee != null) {
            System.out.println("This email already exists!");
        }
        EmployeeEntity employeeEntity1 = employeeRepository1.save(employeeEntity);
        ConfirmationToken confirmationToken = new ConfirmationToken(employeeEntity1);
        confirmationTokenRepository.save(confirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(employeeEntity1.getEmailId());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("EMAIL ADDRESS");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8183/confirm-account?token=" + confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);
        return ResponseEntity.ok(employeeEntity1);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity confirmEmployeeAccount(EmployeeEntity employeeEntity, @RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token == null) {
            System.out.println("The link is invalid or broken!");
        }
        EmployeeEntity employee = employeeRepository1.findByEmailIdIgnoreCase(token.getEmployee().getEmailId());
        employee.setEnabled(true);
        EmployeeEntity employee1 = employeeRepository1.save(employee);
        return ResponseEntity.ok(employee1);
    }
}
