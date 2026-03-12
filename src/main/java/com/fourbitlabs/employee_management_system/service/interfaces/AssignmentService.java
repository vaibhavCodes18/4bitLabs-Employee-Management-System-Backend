package com.fourbitlabs.employee_management_system.service.interfaces;

import com.fourbitlabs.employee_management_system.dto.request.AssignStudentRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.AssignmentResponseDto;

public interface AssignmentService {
    AssignmentResponseDto assignStudent(AssignStudentRequestDto studentRequestDto);
}
