package com.smallproject.SmallProject.controller;

import com.smallproject.SmallProject.service.CompanyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/company")
public class CompanyResource {

    private CompanyService companyService;

    @PostMapping("sign-up")
    public String signUp() {
        return "sign-up";
    }

    @GetMapping("sign-in")
    public String signIn() {
        return "Sign-in";
    }

    @PutMapping("edit")
    public String edit() {
        return "Edit";
    }

    @PutMapping("block")
    public String block() {
        return "Block";
    }
}
