package com.fourbitlabs.employee_management_system.controller;

import com.fourbitlabs.employee_management_system.dto.request.BatchProgressRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.BatchProgressResponseDto;
import com.fourbitlabs.employee_management_system.response.ApiResponse;
import com.fourbitlabs.employee_management_system.service.interfaces.TrainerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TrainerController handles all trainer-related operations.
 * 
 * The trainer is responsible for:
 * - Managing batch progress (add/view progress for assigned batches)
 * 
 * Note: Trainer creation is handled by the AdminController.
 * Note: Batch progress service methods are not yet implemented.
 *       Endpoints will be added here once the service layer supports them.
 * 
 * Planned endpoints:
 * - POST /api/trainer/batch-progress     → Add batch progress
 * - GET  /api/trainer/batch-progress/{batchId} → View batch progress by batch ID
 */
@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @PostMapping("/batch-progress")
    public ResponseEntity<ApiResponse<BatchProgressResponseDto>> addBatchProgress(@Valid @RequestBody BatchProgressRequestDto batchProgressRequestDto) {
        BatchProgressResponseDto responseDto = trainerService.addBatchProgress(batchProgressRequestDto);
        ApiResponse<BatchProgressResponseDto> response = new ApiResponse<>(201, "Batch progress added successfully", responseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/batch-progress/{batchId}")
    public ResponseEntity<ApiResponse<List<BatchProgressResponseDto>>> getBatchProgress(@PathVariable Long batchId) {
        List<BatchProgressResponseDto> progressList = trainerService.getBatchProgress(batchId);
        ApiResponse<List<BatchProgressResponseDto>> response = new ApiResponse<>(200, "Batch progress fetched successfully", progressList);
        return ResponseEntity.ok(response);
    }
}
