package com.oskkar.employeeservice.service;

import com.oskkar.employeeservice.dto.APIResponseDto;
import com.oskkar.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto findEmployeeById(Integer id);
}
