package com.oskkar.departmentservice.service.impl;

import com.oskkar.departmentservice.dto.DepartmentDto;
import com.oskkar.departmentservice.entity.Department;
import com.oskkar.departmentservice.exception.ResourceNotFoundException;
import com.oskkar.departmentservice.mapper.DepartmentMapper;
import com.oskkar.departmentservice.repository.DepartmentRepository;
import com.oskkar.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.MAPPER.departmentDtoToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.MAPPER.departmentToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findByCode(code).orElseThrow(
                () -> new ResourceNotFoundException("Department", "code", code)
        );
        return DepartmentMapper.MAPPER.departmentToDepartmentDto(department);
    }
}
