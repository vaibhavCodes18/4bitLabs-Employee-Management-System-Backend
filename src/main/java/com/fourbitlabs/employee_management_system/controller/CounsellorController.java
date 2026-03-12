package com.fourbitlabs.employee_management_system.controller;

import com.fourbitlabs.employee_management_system.dto.request.StudentRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.StudentResponseDto;
import com.fourbitlabs.employee_management_system.response.ApiResponse;
import com.fourbitlabs.employee_management_system.service.interfaces.CounsellorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * CounsellorController handles all counsellor-related operations.
 * 
 * The counsellor is responsible for:
 * - Creating and managing students
 */
@RestController
@RequestMapping("/api/counsellor")
public class CounsellorController {

    @Autowired
    private CounsellorService counsellorService;

    // ========================
    // Student Management
    // ========================

    /**
     * Create a new student under a counsellor.
     * POST /api/counsellor/students
     */
    @PostMapping("/students")
    public ResponseEntity<ApiResponse<StudentResponseDto>> createStudent(
            @Valid @RequestBody StudentRequestDto studentRequestDto) {
        StudentResponseDto studentResponseDto = counsellorService.createStudent(studentRequestDto);
        ApiResponse<StudentResponseDto> response = new ApiResponse<>(201, "Student created successfully", studentResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
