package com.fourbitlabs.employee_management_system.controller;

import com.fourbitlabs.employee_management_system.dto.request.StudentRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.StudentResponseDto;
import com.fourbitlabs.employee_management_system.response.ApiResponse;
import com.fourbitlabs.employee_management_system.service.CounsellorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/counsellor")
public class StudentController {

    @Autowired
    private CounsellorService counsellorService;

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@RequestBody StudentRequestDto studentRequestDto){
        StudentResponseDto studentResponseDto = counsellorService.createStudent(studentRequestDto);
        ApiResponse<?> apiResponse = new ApiResponse<>(201, "Student added!", studentResponseDto);
        return ResponseEntity.status(201).body(apiResponse);
    }
}
