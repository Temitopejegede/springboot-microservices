package com.temi.departmentservice.service;

import com.temi.departmentservice.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> saveDepartments(List<DepartmentDto> departmentDtos);

    DepartmentDto getDepartmentByCode(String code);

    DepartmentDto getDepartmentById(Long id);

    List<DepartmentDto> getAllDepartments();

    void deleteDepartment(Long id);

    void deleteAllDepartments();

    DepartmentDto updateDepartment(DepartmentDto departmentDto);
}
