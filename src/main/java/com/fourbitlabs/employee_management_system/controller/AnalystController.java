package com.fourbitlabs.employee_management_system.controller;

import com.fourbitlabs.employee_management_system.dto.request.BatchRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.BatchResponseDto;
import com.fourbitlabs.employee_management_system.response.ApiResponse;
import com.fourbitlabs.employee_management_system.service.interfaces.BatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AnalystController handles all analyst-related operations.
 * 
 * The analyst is responsible for:
 * - Creating and managing batches
 */
@RestController
@RequestMapping("/api/analyst")
public class AnalystController {

    @Autowired
    private BatchService batchService;

    // ========================
    // Batch Management
    // ========================

    /**
     * Create a new batch.
     * POST /api/analyst/batches
     */
    @PostMapping("/batches")
    public ResponseEntity<ApiResponse<BatchResponseDto>> createBatch(
            @Valid @RequestBody BatchRequestDto batchRequestDto) {
        BatchResponseDto batchResponseDto = batchService.saveBatch(batchRequestDto);
        ApiResponse<BatchResponseDto> response = new ApiResponse<>(201, "Batch created successfully", batchResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
