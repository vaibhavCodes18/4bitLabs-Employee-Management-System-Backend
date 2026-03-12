package com.fourbitlabs.employee_management_system.service.interfaces;

import com.fourbitlabs.employee_management_system.dto.request.AssignStudentRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.AssignmentResponseDto;
import com.fourbitlabs.employee_management_system.dto.response.AssignmentTransferBatchResponseDto;
import com.fourbitlabs.employee_management_system.dto.response.StudentResponseDto;

import java.util.List;

public interface AssignmentService {
    AssignmentResponseDto assignStudent(AssignStudentRequestDto studentRequestDto);
    AssignmentTransferBatchResponseDto transferBatch(AssignStudentRequestDto studentRequestDto);
    List<StudentResponseDto> fetchAllStudentByBatch(Long batchId);
}
