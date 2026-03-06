package com.fourbitlabs.employee_management_system.service;

import com.fourbitlabs.employee_management_system.dto.request.CreateAdminRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.AdminResponseDto;

public interface UserService {
    AdminResponseDto createAdmin(CreateAdminRequestDto createAdminRequestDto);
}
