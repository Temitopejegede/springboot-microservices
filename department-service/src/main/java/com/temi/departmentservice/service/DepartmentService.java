package com.temi.departmentservice.service;

import com.temi.departmentservice.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode(String code);

    List<DepartmentDto> getAllDepartments();

    void deleteDepartment(Long id);

    void deleteAllDepartments();

    DepartmentDto updateDepartment(DepartmentDto departmentDto);
}
