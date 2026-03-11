package com.fourbitlabs.employee_management_system.service;

import com.fourbitlabs.employee_management_system.dto.request.CounsellorRequestDto;
import com.fourbitlabs.employee_management_system.dto.request.StudentRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.CounsellorResponseDto;
import com.fourbitlabs.employee_management_system.dto.response.StudentResponseDto;
import com.fourbitlabs.employee_management_system.entity.CounsellorProfile;
import com.fourbitlabs.employee_management_system.entity.Student;
import com.fourbitlabs.employee_management_system.entity.User;
import com.fourbitlabs.employee_management_system.enums.Role;
import com.fourbitlabs.employee_management_system.enums.StudentStatus;
import com.fourbitlabs.employee_management_system.enums.UserStatus;
import com.fourbitlabs.employee_management_system.exception.DuplicateResourceException;
import com.fourbitlabs.employee_management_system.exception.ResourceNotFoundException;
import com.fourbitlabs.employee_management_system.repository.CounsellorProfileRepository;
import com.fourbitlabs.employee_management_system.repository.StudentRepository;
import com.fourbitlabs.employee_management_system.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounsellorServiceImpl implements CounsellorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CounsellorProfileRepository counsellorProfileRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public CounsellorResponseDto createCounsellor(CounsellorRequestDto counsellorRequestDto) {
        // Check for duplicate email
        if (userRepository.existsByEmail(counsellorRequestDto.getEmail())) {
            throw new DuplicateResourceException("A user with email '" + counsellorRequestDto.getEmail() + "' already exists");
        }
        User admin = userRepository.findById(counsellorRequestDto.getAdminId())
                .orElseThrow(() -> new ResourceNotFoundException("A admin with this id: "+ counsellorRequestDto.getAdminId() + " is not found."));

        User user = new User();
        user.setName(counsellorRequestDto.getName());
        user.setEmail(counsellorRequestDto.getEmail());
        user.setPhone(counsellorRequestDto.getPhone());
        user.setPassword(counsellorRequestDto.getPassword());
        user.setRole(Role.COUNSELLOR);
        user.setStatus(UserStatus.ACTIVE);
        user.setCreatedByAdmin(admin);
        User savedUser = userRepository.save(user);
        user.getManagedUsers().add(savedUser);

        CounsellorProfile counsellorProfile = new CounsellorProfile();
        counsellorProfile.setDepartment(counsellorRequestDto.getDepartment());
        counsellorProfile.setJoiningDate(counsellorRequestDto.getJoiningDate());
        counsellorProfile.setSalary(counsellorRequestDto.getSalary());
        counsellorProfile.setUser(savedUser);
        CounsellorProfile savedCounsellorProfile = counsellorProfileRepository.save(counsellorProfile);

        return mapToResponseDto(savedUser, savedCounsellorProfile);
    }

    @Override
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
        if (studentRepository.existsByEmail(studentRequestDto.getEmail())) {
            throw new DuplicateResourceException("A student with email '" + studentRequestDto.getEmail() + "' already exists");
        }

        CounsellorProfile counsellorProfile = counsellorProfileRepository.findById(studentRequestDto.getCounsellorId())
                .orElseThrow(() -> new ResourceNotFoundException("A counsellor not found with this id: "+studentRequestDto.getCounsellorId()));

        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());
        student.setPhone(studentRequestDto.getPhone());
        student.setStatus(StudentStatus.ACTIVE);
        student.setJoiningDate(studentRequestDto.getJoiningDate());
        student.setCounsellor(counsellorProfile);
        Student savedStudent = studentRepository.save(student);

        return getStudentResponseDto(savedStudent);
    }

    @NotNull
    private static StudentResponseDto getStudentResponseDto(Student savedStudent) {
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(savedStudent.getId());
        studentResponseDto.setName(savedStudent.getName());
        studentResponseDto.setEmail(savedStudent.getEmail());
        studentResponseDto.setPhone(savedStudent.getPhone());
        studentResponseDto.setStatus(savedStudent.getStatus());
        studentResponseDto.setJoiningDate(savedStudent.getJoiningDate());
        studentResponseDto.setCounsellorId(savedStudent.getCounsellor().getId());
        return studentResponseDto;
    }

    @NotNull
    private static CounsellorResponseDto mapToResponseDto(User savedUser, CounsellorProfile savedCounsellorProfile) {
        CounsellorResponseDto counsellorResponseDto = new CounsellorResponseDto();
        counsellorResponseDto.setId(savedUser.getId());
        counsellorResponseDto.setName(savedUser.getName());
        counsellorResponseDto.setEmail(savedUser.getEmail());
        counsellorResponseDto.setPhone(savedUser.getPhone());
        counsellorResponseDto.setRole(savedUser.getRole());
        counsellorResponseDto.setStatus(savedUser.getStatus());

        counsellorResponseDto.setDepartment(savedCounsellorProfile.getDepartment());
        counsellorResponseDto.setJoiningDate(savedCounsellorProfile.getJoiningDate());
        counsellorResponseDto.setSalary(savedCounsellorProfile.getSalary());
        return counsellorResponseDto;
    }
}
