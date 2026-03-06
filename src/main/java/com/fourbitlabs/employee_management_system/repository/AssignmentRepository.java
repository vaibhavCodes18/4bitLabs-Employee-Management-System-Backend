package com.fourbitlabs.employee_management_system.repository;

import com.fourbitlabs.employee_management_system.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByStudentId(Long studentId);
    List<Assignment> findByBatchId(Long batchId);
}
