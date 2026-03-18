package com.fourbitlabs.employee_management_system.dto.request;

import java.time.LocalDate;

public class UpdateStudentRequestDto {

    private String name;

    private String phone;

    private LocalDate joiningDate;

    public UpdateStudentRequestDto() {
    }

    public UpdateStudentRequestDto(String name, String phone, LocalDate joiningDate) {
        this.name = name;
        this.phone = phone;
        this.joiningDate = joiningDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
}
