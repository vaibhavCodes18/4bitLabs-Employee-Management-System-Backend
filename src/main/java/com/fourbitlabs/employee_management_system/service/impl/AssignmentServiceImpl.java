package com.fourbitlabs.employee_management_system.service.impl;

import com.fourbitlabs.employee_management_system.dto.request.AssignStudentRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.AssignmentResponseDto;
import com.fourbitlabs.employee_management_system.entity.Assignment;
import com.fourbitlabs.employee_management_system.entity.Batch;
import com.fourbitlabs.employee_management_system.entity.Student;
import com.fourbitlabs.employee_management_system.enums.AssignmentStatus;
import com.fourbitlabs.employee_management_system.exception.ResourceNotFoundException;
import com.fourbitlabs.employee_management_system.repository.AssignmentRepository;
import com.fourbitlabs.employee_management_system.repository.BatchRepository;
import com.fourbitlabs.employee_management_system.repository.StudentRepository;
import com.fourbitlabs.employee_management_system.service.interfaces.AssignmentService;
import jakarta.persistence.Access;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BatchRepository batchRepository;

    @Override
    public AssignmentResponseDto assignStudent(AssignStudentRequestDto studentRequestDto) {

        Student student = studentRepository.findById(studentRequestDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("A student with this id: "+ studentRequestDto.getStudentId() + " is not found."));

        Batch batch = batchRepository.findById(studentRequestDto.getBatchId())
                .orElseThrow(() -> new ResourceNotFoundException("A batch with this id: "+ studentRequestDto.getBatchId() + " is not found."));

        Assignment assignment = new Assignment();
        assignment.setStudent(student);
        assignment.setBatch(batch);
        assignment.setAssignedDate(studentRequestDto.getAssignedDate());
        assignment.setStatus(AssignmentStatus.ACTIVE);
        Assignment savedAssignment = assignmentRepository.save(assignment);

        return getAssignmentResponseDto(savedAssignment);
    }

    @NotNull
    private static AssignmentResponseDto getAssignmentResponseDto(Assignment savedAssignment) {
        AssignmentResponseDto assignmentResponseDto = new AssignmentResponseDto();

        assignmentResponseDto.setId(savedAssignment.getId());
        assignmentResponseDto.setBatchId(savedAssignment.getBatch().getId());
        assignmentResponseDto.setBatchName(savedAssignment.getBatch().getName());
        assignmentResponseDto.setStudentId(savedAssignment.getStudent().getId());
        assignmentResponseDto.setStudentName(savedAssignment.getStudent().getName());
        assignmentResponseDto.setStatus(savedAssignment.getStatus());
        assignmentResponseDto.setAssignedDate(savedAssignment.getAssignedDate());
        assignmentResponseDto.setUpdatedAt(savedAssignment.getUpdatedAt());
        return assignmentResponseDto;
    }
}
