package com.fourbitlabs.employee_management_system.controller;

import com.fourbitlabs.employee_management_system.dto.request.BatchRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.BatchResponseDto;
import com.fourbitlabs.employee_management_system.response.ApiResponse;
import com.fourbitlabs.employee_management_system.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/batch")
public class BatchController {
    @Autowired
    private BatchService batchService;

    @PostMapping
    public ResponseEntity<?> createBatch(@RequestBody BatchRequestDto batchRequestDto){
        BatchResponseDto savedBatch = batchService.saveBatch(batchRequestDto);
        ApiResponse<?> apiResponse = new ApiResponse<>(201, "Batch created!", savedBatch);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
