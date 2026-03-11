package com.fourbitlabs.employee_management_system.controller;

import com.fourbitlabs.employee_management_system.dto.request.AnalystRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.AnalystResponseDto;
import com.fourbitlabs.employee_management_system.response.ApiResponse;
import com.fourbitlabs.employee_management_system.service.AnalystService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/analyst")
public class AnalystController {

    @Autowired
    private AnalystService analystService;

    @PostMapping("/{adminId}")
    public ResponseEntity<?> saveAnalyst(@Valid @RequestBody AnalystRequestDto analystRequestDto, @PathVariable("adminId") Long adminId) {
        AnalystResponseDto analystResponseDto = analystService.createAnalyst(analystRequestDto, adminId);
        ApiResponse<?> apiResponse = new ApiResponse<>(201, "Analyst created", analystResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
