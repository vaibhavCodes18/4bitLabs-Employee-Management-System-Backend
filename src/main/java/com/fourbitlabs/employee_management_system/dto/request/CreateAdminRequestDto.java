package com.fourbitlabs.employee_management_system.dto.request;

public class CreateAdminRequestDto extends CreateUserRequestDto {

    public CreateAdminRequestDto() {
        super();
    }

    // Admin has no extra fields beyond the base user fields.
    // Inherits: name, email, password, phone from CreateUserRequestDto.
}
