package com.fourbitlabs.employee_management_system.controller;

import com.fourbitlabs.employee_management_system.dto.request.BatchProgressRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.BatchProgressResponseDto;
import com.fourbitlabs.employee_management_system.response.ApiResponse;
import com.fourbitlabs.employee_management_system.service.interfaces.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/batch-progress")
public class BatchProgressController {

    @Autowired
    private TrainerService trainerService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<BatchProgressResponseDto>> addBatchProgress(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("sessionNumber") Integer sessionNumber,
            @RequestParam("topicCovered") String topicCovered,
            @RequestParam("batchId") Long batchId,
            @RequestParam("trainerId") String trainerId) {

        BatchProgressRequestDto batchProgressRequestDto = new BatchProgressRequestDto(
                title, description, sessionNumber, topicCovered, batchId, Long.parseLong(trainerId));

        BatchProgressResponseDto responseDto = trainerService.addBatchProgress(file, batchProgressRequestDto);
        ApiResponse<BatchProgressResponseDto> response = new ApiResponse<>(201, "Batch progress added successfully", responseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{batchId}")
    public ResponseEntity<ApiResponse<List<BatchProgressResponseDto>>> getBatchProgress(@PathVariable Long batchId) {
        List<BatchProgressResponseDto> progressList = trainerService.getBatchProgress(batchId);
        ApiResponse<List<BatchProgressResponseDto>> response = new ApiResponse<>(200, "Batch progress fetched successfully", progressList);
        return ResponseEntity.ok(response);
    }
}
