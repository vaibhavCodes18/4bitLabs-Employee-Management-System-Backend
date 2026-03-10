package com.fourbitlabs.employee_management_system.service;

import com.fourbitlabs.employee_management_system.dto.request.CreateAdminRequestDto;
import com.fourbitlabs.employee_management_system.dto.request.CreateTrainerRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.AdminResponseDto;
import com.fourbitlabs.employee_management_system.dto.response.TrainerResponseDto;
import com.fourbitlabs.employee_management_system.entity.TrainerProfile;
import com.fourbitlabs.employee_management_system.entity.User;
import com.fourbitlabs.employee_management_system.enums.Role;
import com.fourbitlabs.employee_management_system.enums.UserStatus;
import com.fourbitlabs.employee_management_system.exception.DuplicateResourceException;
import com.fourbitlabs.employee_management_system.repository.TrainerProfileRepository;
import com.fourbitlabs.employee_management_system.repository.UserRepository;
import com.fourbitlabs.employee_management_system.response.ApiResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainerProfileRepository trainerProfileRepository;

    @Override
    public AdminResponseDto createAdmin(CreateAdminRequestDto createAdminRequestDto) {
        // Check for duplicate email
        if (userRepository.existsByEmail(createAdminRequestDto.getEmail())) {
            throw new DuplicateResourceException("A user with email '" + createAdminRequestDto.getEmail() + "' already exists");
        }

        User user = new User();
        user.setName(createAdminRequestDto.getName());
        user.setEmail(createAdminRequestDto.getEmail());
        user.setPassword(createAdminRequestDto.getPassword());
        user.setPhone(createAdminRequestDto.getPhone());
        user.setRole(Role.ADMIN);
        user.setStatus(UserStatus.ACTIVE);

        User savedUser = userRepository.save(user);

        AdminResponseDto responseDto = new AdminResponseDto();
        responseDto.setId(savedUser.getId());
        responseDto.setName(savedUser.getName());
        responseDto.setEmail(savedUser.getEmail());
        responseDto.setPhone(savedUser.getPhone());
        responseDto.setRole(savedUser.getRole());
        responseDto.setStatus(savedUser.getStatus());

        return responseDto;
    }

    @Override
    public TrainerResponseDto createTrainer(CreateTrainerRequestDto trainerRequestDto) {
        // Check for duplicate email
        if (userRepository.existsByEmail(trainerRequestDto.getEmail())) {
            throw new DuplicateResourceException("A user with email '" + trainerRequestDto.getEmail() + "' already exists");
        }

        User user = new User();
        user.setName(trainerRequestDto.getName());
        user.setEmail(trainerRequestDto.getEmail());
        user.setPhone(trainerRequestDto.getPhone());
        user.setPassword(trainerRequestDto.getPassword());
        user.setRole(Role.TRAINER);
        user.setStatus(UserStatus.ACTIVE);
        User savedUser = userRepository.save(user);

        TrainerProfile trainerProfile = new TrainerProfile();
        trainerProfile.setSpecialization(trainerRequestDto.getSpecialization());
        trainerProfile.setExperienceYears(trainerRequestDto.getExperienceYears());
        trainerProfile.setQualification(trainerRequestDto.getQualification());
        trainerProfile.setSalary(trainerRequestDto.getSalary());
        trainerProfile.setJoiningDate(trainerRequestDto.getJoiningDate());
        trainerProfile.setUser(savedUser);
        TrainerProfile savedTrainerProfile = trainerProfileRepository.save(trainerProfile);



        return getTrainerResponseDto(savedUser, savedTrainerProfile);
    }

    @NotNull
    private static TrainerResponseDto getTrainerResponseDto(User savedUser, TrainerProfile savedTrainerProfile) {
        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(savedUser.getId());
        trainerResponseDto.setName(savedUser.getName());
        trainerResponseDto.setEmail(savedUser.getEmail());
        trainerResponseDto.setPhone(savedUser.getPhone());
        trainerResponseDto.setRole(savedUser.getRole());
        trainerResponseDto.setStatus(savedUser.getStatus());

        trainerResponseDto.setSpecialization(savedTrainerProfile.getSpecialization());
        trainerResponseDto.setQualification(savedTrainerProfile.getQualification());
        trainerResponseDto.setExperienceYears(savedTrainerProfile.getExperienceYears());
        trainerResponseDto.setJoiningDate(savedTrainerProfile.getJoiningDate());
        trainerResponseDto.setSalary(savedTrainerProfile.getSalary());
        return trainerResponseDto;
    }
}
