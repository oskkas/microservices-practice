package com.oskkar.employeeservice.service.impl;

import com.oskkar.employeeservice.dto.APIResponseDto;
import com.oskkar.employeeservice.dto.DepartmentDto;
import com.oskkar.employeeservice.dto.EmployeeDto;
import com.oskkar.employeeservice.entity.Employee;
import com.oskkar.employeeservice.exception.ResourceNotFoundException;
import com.oskkar.employeeservice.mapper.EmployeeMapper;
import com.oskkar.employeeservice.repository.EmployeeRepository;
import com.oskkar.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;
    RestTemplate restTemplate;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.MAPPER.employeeDtoToEmployee(employeeDto);
        Employee employeeSaved = employeeRepository.save(employee);
        return EmployeeMapper.MAPPER.employeeToEmployeeDto(employeeSaved);
    }

    @Override
    public APIResponseDto findEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id", "code", id)
        );

        ResponseEntity<DepartmentDto> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/departments/" + employee
                        .getCode(), DepartmentDto.class);

        DepartmentDto departmentDto = responseEntity.getBody();
        EmployeeDto employeeDto = EmployeeMapper.MAPPER.employeeToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
