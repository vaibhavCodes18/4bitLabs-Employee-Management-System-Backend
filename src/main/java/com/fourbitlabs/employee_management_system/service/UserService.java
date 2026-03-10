package com.fourbitlabs.employee_management_system.service;

import com.fourbitlabs.employee_management_system.dto.request.CreateAdminRequestDto;
import com.fourbitlabs.employee_management_system.dto.request.CreateTrainerRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.AdminResponseDto;
import com.fourbitlabs.employee_management_system.dto.response.TrainerResponseDto;

public interface UserService {
    AdminResponseDto createAdmin(CreateAdminRequestDto createAdminRequestDto);
    TrainerResponseDto createTrainer(CreateTrainerRequestDto trainerRequestDto);
}
