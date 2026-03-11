package com.fourbitlabs.employee_management_system.controller;

import com.fourbitlabs.employee_management_system.dto.request.CounsellorRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.CounsellorResponseDto;
import com.fourbitlabs.employee_management_system.response.ApiResponse;
import com.fourbitlabs.employee_management_system.service.CounsellorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/counsellor")
public class CounsellorController {

    @Autowired
    private CounsellorService counsellorService;

    @PostMapping
    public ResponseEntity<?> saveCounsellor(@Valid @RequestBody CounsellorRequestDto counsellorRequestDto) {
        CounsellorResponseDto counsellorResponseDto = counsellorService.createCounsellor(counsellorRequestDto);
        ApiResponse<?> apiResponse = new ApiResponse<>(201, "Counsellor created", counsellorResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
