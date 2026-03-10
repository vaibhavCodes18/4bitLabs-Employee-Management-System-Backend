package com.fourbitlabs.employee_management_system.service;

import com.fourbitlabs.employee_management_system.dto.request.AdminRequestDto;
import com.fourbitlabs.employee_management_system.dto.request.AnalystRequestDto;
import com.fourbitlabs.employee_management_system.dto.request.CounsellorRequestDto;
import com.fourbitlabs.employee_management_system.dto.request.TrainerRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.AdminResponseDto;
import com.fourbitlabs.employee_management_system.dto.response.AnalystResponseDto;
import com.fourbitlabs.employee_management_system.dto.response.CounsellorResponseDto;
import com.fourbitlabs.employee_management_system.dto.response.TrainerResponseDto;
import com.fourbitlabs.employee_management_system.entity.AnalystProfile;
import com.fourbitlabs.employee_management_system.entity.CounsellorProfile;
import com.fourbitlabs.employee_management_system.entity.TrainerProfile;
import com.fourbitlabs.employee_management_system.entity.User;
import com.fourbitlabs.employee_management_system.enums.Role;
import com.fourbitlabs.employee_management_system.enums.UserStatus;
import com.fourbitlabs.employee_management_system.exception.DuplicateResourceException;
import com.fourbitlabs.employee_management_system.repository.AnalystProfileRepository;
import com.fourbitlabs.employee_management_system.repository.CounsellorProfileRepository;
import com.fourbitlabs.employee_management_system.repository.TrainerProfileRepository;
import com.fourbitlabs.employee_management_system.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainerProfileRepository trainerProfileRepository;
    @Autowired
    private AnalystProfileRepository analystProfileRepository;
    @Autowired
    private CounsellorProfileRepository counsellorProfileRepository;

    @Override
    public AdminResponseDto createAdmin(AdminRequestDto createAdminRequestDto) {
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
    public TrainerResponseDto createTrainer(TrainerRequestDto trainerRequestDto) {
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

    @Override
    public AnalystResponseDto createAnalyst(AnalystRequestDto analystRequestDto) {
        // Check for duplicate email
        if (userRepository.existsByEmail(analystRequestDto.getEmail())) {
            throw new DuplicateResourceException("A user with email '" + analystRequestDto.getEmail() + "' already exists");
        }

        User user = new User();
        user.setName(analystRequestDto.getName());
        user.setEmail(analystRequestDto.getEmail());
        user.setPhone(analystRequestDto.getPhone());
        user.setPassword(analystRequestDto.getPassword());
        user.setRole(Role.ANALYST);
        user.setStatus(UserStatus.ACTIVE);
        User savedUser = userRepository.save(user);

        AnalystProfile analystProfile = new AnalystProfile();
        analystProfile.setDepartment(analystRequestDto.getDepartment());
        analystProfile.setJoiningDate(analystRequestDto.getJoiningDate());
        analystProfile.setSalary(analystRequestDto.getSalary());
        analystProfile.setUser(savedUser);
        AnalystProfile savedAnalystProfile = analystProfileRepository.save(analystProfile);

        return getAnalystResponseDto(savedUser, savedAnalystProfile);
    }

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

        return getCounsellorResponseDto(savedUser, savedCounsellorProfile);
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

    @NotNull
    private static AnalystResponseDto getAnalystResponseDto(User savedUser, AnalystProfile savedAnalystProfile) {
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

    @NotNull
    private static CounsellorResponseDto getCounsellorResponseDto(User savedUser, CounsellorProfile savedCounsellorProfile) {
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
