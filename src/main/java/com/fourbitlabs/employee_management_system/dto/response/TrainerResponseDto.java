package com.fourbitlabs.employee_management_system.dto.response;

import com.fourbitlabs.employee_management_system.enums.Role;
import com.fourbitlabs.employee_management_system.enums.UserStatus;

import java.time.LocalDate;

public class TrainerReaponseDto extends UserResponseDto{
    private Long id;
    private String specialization;

    private Integer experienceYears;

    private String qualification;

    private LocalDate joiningDate;

    private Double salary;

    public TrainerReaponseDto(Long id, String specialization, Integer experienceYears, String qualification, LocalDate joiningDate, Double salary) {
        this.id = id;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.qualification = qualification;
        this.joiningDate = joiningDate;
        this.salary = salary;
    }

    public TrainerReaponseDto(Long id, String name, String email, String phone, Role role, UserStatus status, Long id1, String specialization, Integer experienceYears, String qualification, LocalDate joiningDate, Double salary) {
        super(id, name, email, phone, role, status);
        this.id = id1;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.qualification = qualification;
        this.joiningDate = joiningDate;
        this.salary = salary;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
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
