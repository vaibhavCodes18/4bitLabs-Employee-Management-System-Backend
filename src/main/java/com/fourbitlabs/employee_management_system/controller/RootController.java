package com.fourbitlabs.employee_management_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping("/api")
    public String helloMsg(){
        return "Welcome to EMS backend!";
    }
}
