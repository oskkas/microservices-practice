package com.oskkar.employeeservice.service.impl;

import com.oskkar.employeeservice.dto.EmployeeDto;
import com.oskkar.employeeservice.entity.Employee;
import com.oskkar.employeeservice.exception.ResourceNotFoundException;
import com.oskkar.employeeservice.mapper.EmployeeMapper;
import com.oskkar.employeeservice.repository.EmployeeRepository;
import com.oskkar.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.MAPPER.employeeDtoToEmployee(employeeDto);
        Employee employeeSaved = employeeRepository.save(employee);
        return EmployeeMapper.MAPPER.employeeToEmployeeDto(employeeSaved);
    }

    @Override
    public EmployeeDto findEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );
        return EmployeeMapper.MAPPER.employeeToEmployeeDto(employee);
    }
}
