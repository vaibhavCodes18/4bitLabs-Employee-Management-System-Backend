package com.fourbitlabs.employee_management_system.service;

import com.fourbitlabs.employee_management_system.dto.request.AnalystRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.AnalystResponseDto;

public interface AnalystService {
    AnalystResponseDto createAnalyst(AnalystRequestDto analystRequestDto);
}
