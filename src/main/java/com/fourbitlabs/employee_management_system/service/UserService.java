package com.fourbitlabs.employee_management_system.service;

import com.fourbitlabs.employee_management_system.dto.request.AdminRequestDto;
import com.fourbitlabs.employee_management_system.dto.request.AnalystRequestDto;
import com.fourbitlabs.employee_management_system.dto.request.CounsellorRequestDto;
import com.fourbitlabs.employee_management_system.dto.request.TrainerRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.AdminResponseDto;
import com.fourbitlabs.employee_management_system.dto.response.AnalystResponseDto;
import com.fourbitlabs.employee_management_system.dto.response.CounsellorResponseDto;
import com.fourbitlabs.employee_management_system.dto.response.TrainerResponseDto;

public interface UserService {
    AdminResponseDto createAdmin(AdminRequestDto createAdminRequestDto);
    TrainerResponseDto createTrainer(TrainerRequestDto trainerRequestDto);
    AnalystResponseDto createAnalyst(AnalystRequestDto analystRequestDto);
    CounsellorResponseDto createCounsellor(CounsellorRequestDto counsellorRequestDto);
}
