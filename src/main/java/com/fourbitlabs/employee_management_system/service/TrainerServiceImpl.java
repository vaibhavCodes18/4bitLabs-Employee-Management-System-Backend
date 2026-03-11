package com.fourbitlabs.employee_management_system.service;

import com.fourbitlabs.employee_management_system.dto.request.TrainerRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.TrainerResponseDto;
import com.fourbitlabs.employee_management_system.entity.TrainerProfile;
import com.fourbitlabs.employee_management_system.entity.User;
import com.fourbitlabs.employee_management_system.enums.Role;
import com.fourbitlabs.employee_management_system.enums.UserStatus;
import com.fourbitlabs.employee_management_system.exception.DuplicateResourceException;
import com.fourbitlabs.employee_management_system.exception.ResourceNotFoundException;
import com.fourbitlabs.employee_management_system.repository.TrainerProfileRepository;
import com.fourbitlabs.employee_management_system.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainerProfileRepository trainerProfileRepository;

    @Override
    public TrainerResponseDto createTrainer(TrainerRequestDto trainerRequestDto, Long adminId) {
        // Check for duplicate email
        if (userRepository.existsByEmail(trainerRequestDto.getEmail())) {
            throw new DuplicateResourceException("A user with email '" + trainerRequestDto.getEmail() + "' already exists");
        }
        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("A admin with this id: "+ adminId + " is not found."));

        User user = new User();
        user.setName(trainerRequestDto.getName());
        user.setEmail(trainerRequestDto.getEmail());
        user.setPhone(trainerRequestDto.getPhone());
        user.setPassword(trainerRequestDto.getPassword());
        user.setRole(Role.TRAINER);
        user.setStatus(UserStatus.ACTIVE);
        user.setCreatedByAdmin(admin);
        User savedUser = userRepository.save(user);
        user.getManagedUsers().add(savedUser);

        TrainerProfile trainerProfile = new TrainerProfile();
        trainerProfile.setSpecialization(trainerRequestDto.getSpecialization());
        trainerProfile.setExperienceYears(trainerRequestDto.getExperienceYears());
        trainerProfile.setQualification(trainerRequestDto.getQualification());
        trainerProfile.setSalary(trainerRequestDto.getSalary());
        trainerProfile.setJoiningDate(trainerRequestDto.getJoiningDate());
        trainerProfile.setUser(savedUser);
        TrainerProfile savedTrainerProfile = trainerProfileRepository.save(trainerProfile);

        return mapToResponseDto(savedUser, savedTrainerProfile);
    }

    @NotNull
    private static TrainerResponseDto mapToResponseDto(User savedUser, TrainerProfile savedTrainerProfile) {
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
