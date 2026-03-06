package com.fourbitlabs.employee_management_system.service;

import com.fourbitlabs.employee_management_system.dto.request.CreateAdminRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.AdminResponseDto;
import com.fourbitlabs.employee_management_system.entity.User;
import com.fourbitlabs.employee_management_system.enums.Role;
import com.fourbitlabs.employee_management_system.enums.UserStatus;
import com.fourbitlabs.employee_management_system.exception.DuplicateResourceException;
import com.fourbitlabs.employee_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public AdminResponseDto createAdmin(CreateAdminRequestDto createAdminRequestDto) {
        // Check for duplicate email
        if (userRepository.existsByEmail(createAdminRequestDto.getEmail())) {
            throw new DuplicateResourceException("A user with email '" + createAdminRequestDto.getEmail() + "' already exists");
        }
        System.out.println(createAdminRequestDto.getEmail());
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
}
