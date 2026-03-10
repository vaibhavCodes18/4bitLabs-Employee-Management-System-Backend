package com.fourbitlabs.employee_management_system.dto.request;

public class CreateAdminRequestDto extends CreateUserRequestDto {

    public CreateAdminRequestDto() {
        super();
    }

    public CreateAdminRequestDto(String name, String email, String password, String phone) {
        super(name, email, password, phone);
    }
// Admin has no extra fields beyond the base user fields.
    // Inherits: name, email, password, phone from CreateUserRequestDto.
}
