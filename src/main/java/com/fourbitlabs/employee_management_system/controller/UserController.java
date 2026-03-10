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
import com.fourbitlabs.employee_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String helloMsg(){
        return "Welcome to EMS backend!";
    }

    @PostMapping("/admin")
    public ResponseEntity<?> saveAdmin(@Valid @RequestBody AdminRequestDto createAdminRequestDto){
        AdminResponseDto adminResponseDto = userService.createAdmin(createAdminRequestDto);
        ApiResponse<?> responseDtoApiResponse = new ApiResponse<>(201, "Admin created successfully", adminResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDtoApiResponse);
    }

    @PostMapping("/trainer")
    public ResponseEntity<?> saveTrainer(@RequestBody TrainerRequestDto trainerRequestDto){
        TrainerResponseDto trainerResponseDto = userService.createTrainer(trainerRequestDto);
        ApiResponse<?> apiResponse = new ApiResponse<>(201, "Trainer created", trainerResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/analyst")
    public ResponseEntity<?> saveAnalyst(@RequestBody AnalystRequestDto analystRequestDto){
        AnalystResponseDto analystResponseDto = userService.createAnalyst(analystRequestDto);
        ApiResponse<?> apiResponse = new ApiResponse<>(201, "Analyst created", analystResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/counsellor")
    public ResponseEntity<?> saveCounsellor(@RequestBody CounsellorRequestDto counsellorRequestDto){
        CounsellorResponseDto counsellorResponseDto = userService.createCounsellor(counsellorRequestDto);
        ApiResponse<?> apiResponse = new ApiResponse<>(201, "Counsellor created", counsellorResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
