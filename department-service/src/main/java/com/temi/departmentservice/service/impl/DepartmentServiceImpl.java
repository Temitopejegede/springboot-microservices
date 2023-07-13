package com.temi.departmentservice.service.impl;

import com.temi.departmentservice.dto.DepartmentDto;
import com.temi.departmentservice.entity.Department;
import com.temi.departmentservice.mapper.AutoDepartmentMapper;
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

//        Department department = new Department(
//                departmentDto.getId(),
//                departmentDto.getDepartName(),
//                departmentDto.getDepartmentDescription(),
//                departmentDto.getDepartmentCode()
//        );

        Department department = AutoDepartmentMapper.MAPPER.mapToDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);

//        DepartmentDto savedDepartmentDto = new DepartmentDto(
//                department.getId(),
//                departmentDto.getDepartName(),
//                departmentDto.getDepartmentDescription(),
//                departmentDto.getDepartmentCode()
//        );

        DepartmentDto savedDepartmentDto = AutoDepartmentMapper.MAPPER.mapToDepartmentDto(savedDepartment);


        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Department department = departmentRepository.findByDepartmentCode(departmentCode);

//        DepartmentDto departmentDto = new DepartmentDto(
//                department.getId(),
//                department.getDepartName(),
//                department.getDepartmentDescription(),
//                department.getDepartmentCode()
//        );

        DepartmentDto departmentDto = AutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);


        return departmentDto;
    }
}
