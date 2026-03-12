package com.fourbitlabs.employee_management_system.controller;

import com.fourbitlabs.employee_management_system.dto.request.AdminRequestDto;
import com.fourbitlabs.employee_management_system.dto.request.AnalystRequestDto;
import com.fourbitlabs.employee_management_system.dto.request.CounsellorRequestDto;
import com.fourbitlabs.employee_management_system.dto.request.TrainerRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.AdminResponseDto;
import com.fourbitlabs.employee_management_system.dto.response.AnalystResponseDto;
import com.fourbitlabs.employee_management_system.dto.response.CounsellorResponseDto;
import com.fourbitlabs.employee_management_system.dto.response.TrainerResponseDto;
import com.fourbitlabs.employee_management_system.response.ApiResponse;
import com.fourbitlabs.employee_management_system.service.interfaces.AdminService;
import com.fourbitlabs.employee_management_system.service.interfaces.AnalystService;
import com.fourbitlabs.employee_management_system.service.interfaces.CounsellorService;
import com.fourbitlabs.employee_management_system.service.interfaces.TrainerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AdminController handles all admin-level operations.
 * 
 * The admin is responsible for:
 * - Self-registration (creating the admin account)
 * - Creating and managing Trainers, Analysts, and Counsellors
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private AnalystService analystService;

    @Autowired
    private CounsellorService counsellorService;

    // ========================
    // Admin Self-Registration
    // ========================

    /**
     * Register a new admin account.
     * POST /api/admin/register
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AdminResponseDto>> registerAdmin(
            @Valid @RequestBody AdminRequestDto adminRequestDto) {
        AdminResponseDto adminResponseDto = adminService.createAdmin(adminRequestDto);
        ApiResponse<AdminResponseDto> response = new ApiResponse<>(201, "Admin registered successfully", adminResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ========================
    // Trainer Management
    // ========================

    /**
     * Create a new trainer under an admin.
     * POST /api/admin/trainers
     */
    @PostMapping("/trainers")
    public ResponseEntity<ApiResponse<TrainerResponseDto>> createTrainer(
            @Valid @RequestBody TrainerRequestDto trainerRequestDto) {
        TrainerResponseDto trainerResponseDto = trainerService.createTrainer(trainerRequestDto);
        ApiResponse<TrainerResponseDto> response = new ApiResponse<>(201, "Trainer created successfully", trainerResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ========================
    // Analyst Management
    // ========================

    /**
     * Create a new analyst under an admin.
     * POST /api/admin/analysts
     */
    @PostMapping("/analysts")
    public ResponseEntity<ApiResponse<AnalystResponseDto>> createAnalyst(
            @Valid @RequestBody AnalystRequestDto analystRequestDto) {
        AnalystResponseDto analystResponseDto = analystService.createAnalyst(analystRequestDto);
        ApiResponse<AnalystResponseDto> response = new ApiResponse<>(201, "Analyst created successfully", analystResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ========================
    // Counsellor Management
    // ========================

    /**
     * Create a new counsellor under an admin.
     * POST /api/admin/counsellors
     */
    @PostMapping("/counsellors")
    public ResponseEntity<ApiResponse<CounsellorResponseDto>> createCounsellor(
            @Valid @RequestBody CounsellorRequestDto counsellorRequestDto) {
        CounsellorResponseDto counsellorResponseDto = counsellorService.createCounsellor(counsellorRequestDto);
        ApiResponse<CounsellorResponseDto> response = new ApiResponse<>(201, "Counsellor created successfully", counsellorResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
