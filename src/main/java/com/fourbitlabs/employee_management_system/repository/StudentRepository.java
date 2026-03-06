package com.fourbitlabs.employee_management_system.repository;

import com.fourbitlabs.employee_management_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByCounsellorId(Long counsellorId);
}
