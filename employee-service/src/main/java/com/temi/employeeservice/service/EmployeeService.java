package com.temi.employeeservice.service;

import com.temi.employeeservice.dto.ApiResponseDto;
import com.temi.employeeservice.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> saveEmployees(List<EmployeeDto> employeeDtos);

    ApiResponseDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(EmployeeDto employeeDto);

    void deleteEmployee(Long id);

    void deleteAllEmployees();
}
