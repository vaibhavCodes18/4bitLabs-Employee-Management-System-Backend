package com.fourbitlabs.employee_management_system.dto.request;

import java.time.LocalDate;

public class CounsellorRequestDto extends UserRequestDto{
    private String department;

    private LocalDate joiningDate;

    private Double salary;

    public CounsellorRequestDto() {

        this.joiningDate = LocalDate.now();
    }

    public CounsellorRequestDto(String department, Double salary) {
        this.department = department;
        this.salary = salary;
        this.joiningDate = LocalDate.now();
    }

    public CounsellorRequestDto(String name, String email, String password, String phone, String department, LocalDate joiningDate, Double salary) {
        super(name, email, password, phone);
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
