package com.fourbitlabs.employee_management_system.service.impl;

import com.fourbitlabs.employee_management_system.service.interfaces.*;

import com.fourbitlabs.employee_management_system.dto.request.AnalystRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.AnalystResponseDto;
import com.fourbitlabs.employee_management_system.entity.AnalystProfile;
import com.fourbitlabs.employee_management_system.entity.User;
import com.fourbitlabs.employee_management_system.enums.Role;
import com.fourbitlabs.employee_management_system.enums.UserStatus;
import com.fourbitlabs.employee_management_system.exception.DuplicateResourceException;
import com.fourbitlabs.employee_management_system.exception.ResourceNotFoundException;
import com.fourbitlabs.employee_management_system.repository.AnalystProfileRepository;
import com.fourbitlabs.employee_management_system.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalystServiceImpl implements AnalystService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnalystProfileRepository analystProfileRepository;

    @Override
    public AnalystResponseDto createAnalyst(AnalystRequestDto analystRequestDto) {
        // Check for duplicate email
        if (userRepository.existsByEmail(analystRequestDto.getEmail())) {
            throw new DuplicateResourceException("A user with email '" + analystRequestDto.getEmail() + "' already exists");
        }
        User admin = userRepository.findById(analystRequestDto.getAdminId())
                .orElseThrow(() -> new ResourceNotFoundException("A admin with this id: "+ analystRequestDto.getAdminId() + " is not found."));


        User user = new User();
        user.setName(analystRequestDto.getName());
        user.setEmail(analystRequestDto.getEmail());
        user.setPhone(analystRequestDto.getPhone());
        user.setPassword(analystRequestDto.getPassword());
        user.setRole(Role.ANALYST);
        user.setStatus(UserStatus.ACTIVE);
        user.setCreatedByAdmin(admin);
        User savedUser = userRepository.save(user);
        user.getManagedUsers().add(savedUser);

        AnalystProfile analystProfile = new AnalystProfile();
        analystProfile.setDepartment(analystRequestDto.getDepartment());
        analystProfile.setJoiningDate(analystRequestDto.getJoiningDate());
        analystProfile.setSalary(analystRequestDto.getSalary());
        analystProfile.setUser(savedUser);
        AnalystProfile savedAnalystProfile = analystProfileRepository.save(analystProfile);

        return mapToResponseDto(savedUser, savedAnalystProfile);
    }

    @NotNull
    private static AnalystResponseDto mapToResponseDto(User savedUser, AnalystProfile savedAnalystProfile) {
        AnalystResponseDto analystResponseDto = new AnalystResponseDto();
        analystResponseDto.setId(savedUser.getId());
        analystResponseDto.setName(savedUser.getName());
        analystResponseDto.setEmail(savedUser.getEmail());
        analystResponseDto.setPhone(savedUser.getPhone());
        analystResponseDto.setRole(savedUser.getRole());
        analystResponseDto.setStatus(savedUser.getStatus());

        analystResponseDto.setDepartment(savedAnalystProfile.getDepartment());
        analystResponseDto.setJoiningDate(savedAnalystProfile.getJoiningDate());
        analystResponseDto.setSalary(savedAnalystProfile.getSalary());
        return analystResponseDto;
    }
}
