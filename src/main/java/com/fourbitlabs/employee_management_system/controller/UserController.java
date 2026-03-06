package com.fourbitlabs.employee_management_system.controller;

import com.fourbitlabs.employee_management_system.dto.request.CreateAdminRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.AdminResponseDto;
import com.fourbitlabs.employee_management_system.response.ApiResponse;
import com.fourbitlabs.employee_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String helloMsg(){
        return "Welcome to EMS backend!";
    }

    @PostMapping
    public ResponseEntity<?> saveAdmin(@Valid @RequestBody CreateAdminRequestDto createAdminRequestDto){
        AdminResponseDto adminResponseDto = userService.createAdmin(createAdminRequestDto);
        ApiResponse<?> responseDtoApiResponse = new ApiResponse<>(201, "Admin created successfully", adminResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDtoApiResponse);
    }
}
