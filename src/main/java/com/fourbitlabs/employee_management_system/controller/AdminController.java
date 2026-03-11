package com.fourbitlabs.employee_management_system.controller;

import com.fourbitlabs.employee_management_system.dto.request.AdminRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.AdminResponseDto;
import com.fourbitlabs.employee_management_system.response.ApiResponse;
import com.fourbitlabs.employee_management_system.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin")
    public ResponseEntity<?> saveAdmin(@Valid @RequestBody AdminRequestDto createAdminRequestDto) {
        AdminResponseDto adminResponseDto = adminService.createAdmin(createAdminRequestDto);
        ApiResponse<?> responseDtoApiResponse = new ApiResponse<>(201, "Admin created successfully", adminResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDtoApiResponse);
    }
}
