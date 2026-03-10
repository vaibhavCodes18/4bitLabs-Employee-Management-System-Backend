package com.fourbitlabs.employee_management_system.service;

import com.fourbitlabs.employee_management_system.dto.request.CounsellorRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.CounsellorResponseDto;
import com.fourbitlabs.employee_management_system.entity.CounsellorProfile;
import com.fourbitlabs.employee_management_system.entity.User;
import com.fourbitlabs.employee_management_system.enums.Role;
import com.fourbitlabs.employee_management_system.enums.UserStatus;
import com.fourbitlabs.employee_management_system.exception.DuplicateResourceException;
import com.fourbitlabs.employee_management_system.repository.CounsellorProfileRepository;
import com.fourbitlabs.employee_management_system.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounsellorServiceImpl implements CounsellorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CounsellorProfileRepository counsellorProfileRepository;

    @Override
    public CounsellorResponseDto createCounsellor(CounsellorRequestDto counsellorRequestDto) {
        // Check for duplicate email
        if (userRepository.existsByEmail(counsellorRequestDto.getEmail())) {
            throw new DuplicateResourceException("A user with email '" + counsellorRequestDto.getEmail() + "' already exists");
        }

        User user = new User();
        user.setName(counsellorRequestDto.getName());
        user.setEmail(counsellorRequestDto.getEmail());
        user.setPhone(counsellorRequestDto.getPhone());
        user.setPassword(counsellorRequestDto.getPassword());
        user.setRole(Role.COUNSELLOR);
        user.setStatus(UserStatus.ACTIVE);
        User savedUser = userRepository.save(user);

        CounsellorProfile counsellorProfile = new CounsellorProfile();
        counsellorProfile.setDepartment(counsellorRequestDto.getDepartment());
        counsellorProfile.setJoiningDate(counsellorRequestDto.getJoiningDate());
        counsellorProfile.setSalary(counsellorRequestDto.getSalary());
        counsellorProfile.setUser(savedUser);
        CounsellorProfile savedCounsellorProfile = counsellorProfileRepository.save(counsellorProfile);

        return mapToResponseDto(savedUser, savedCounsellorProfile);
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
