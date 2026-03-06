package com.fourbitlabs.employee_management_system.dto.request;

import com.fourbitlabs.employee_management_system.enums.Role;
import com.fourbitlabs.employee_management_system.enums.UserStatus;
import lombok.Data;

@Data
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
    private String phone;
    private Role role;
    private UserStatus userStatus;
}
