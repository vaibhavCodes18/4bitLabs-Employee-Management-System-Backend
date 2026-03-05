package com.fourbitlabs.employee_management_system.response;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private Integer status;
    private String msg;
    private T data;
}
