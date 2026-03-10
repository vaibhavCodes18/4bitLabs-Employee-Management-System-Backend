package com.fourbitlabs.employee_management_system.service;

import com.fourbitlabs.employee_management_system.dto.request.CounsellorRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.CounsellorResponseDto;

public interface CounsellorService {
    CounsellorResponseDto createCounsellor(CounsellorRequestDto counsellorRequestDto);
}
