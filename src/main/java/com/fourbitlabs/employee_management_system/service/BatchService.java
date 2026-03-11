package com.fourbitlabs.employee_management_system.service;

import com.fourbitlabs.employee_management_system.dto.request.BatchRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.BatchResponseDto;

public interface BatchService {
    BatchResponseDto saveBatch(BatchRequestDto batchRequestDto);
}
