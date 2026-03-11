package com.fourbitlabs.employee_management_system.service;

import com.fourbitlabs.employee_management_system.dto.request.CounsellorRequestDto;
import com.fourbitlabs.employee_management_system.dto.request.StudentRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.CounsellorResponseDto;
import com.fourbitlabs.employee_management_system.dto.response.StudentResponseDto;

public interface CounsellorService {
    CounsellorResponseDto createCounsellor(CounsellorRequestDto counsellorRequestDto);
    StudentResponseDto createStudent(StudentRequestDto studentRequestDto);
}
