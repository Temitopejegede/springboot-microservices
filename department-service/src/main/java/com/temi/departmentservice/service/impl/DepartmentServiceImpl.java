package com.temi.departmentservice.service.impl;

import com.temi.departmentservice.dto.DepartmentDto;
import com.temi.departmentservice.entity.Department;
import com.temi.departmentservice.repository.DepartmentRepository;
import com.temi.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
   private DepartmentRepository departmentRepository;



    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        //

        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );

        Department savedDepartment = departmentRepository.save(department);

        DepartmentDto savedDepartmentDto = new DepartmentDto(
                department.getId(),
                departmentDto.getDepartName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );


        return savedDepartmentDto;
    }
}
