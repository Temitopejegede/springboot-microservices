package com.temi.departmentservice.service.impl;

import com.temi.departmentservice.dto.DepartmentDto;
import com.temi.departmentservice.entity.Department;
import com.temi.departmentservice.exception.DepartmentCodeAlreadyExistsException;
import com.temi.departmentservice.exception.DepartmentNameAlreadyExistsException;
import com.temi.departmentservice.exception.ResourceNotFoundException;
import com.temi.departmentservice.mapper.AutoDepartmentMapper;
import com.temi.departmentservice.repository.DepartmentRepository;
import com.temi.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

   private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        Optional<Department> optionalDepartment = departmentRepository.findByDepartmentCode(departmentDto.getDepartmentCode());

        if(optionalDepartment.isPresent()){
            if(optionalDepartment.get().getDepartmentCode().equals(departmentDto.getDepartmentCode())){
                throw  new DepartmentCodeAlreadyExistsException("Department Code Already Exists");
            }
            else {
                throw new DepartmentNameAlreadyExistsException("Department Name Already Exists");
            }
        }



//        Department department = new Department(
//                departmentDto.getId(),
//                departmentDto.getDepartmentName(),
//                departmentDto.getDepartmentDescription(),
//                departmentDto.getDepartmentCode()
//        );
        Department department = AutoDepartmentMapper.MAPPER.mapToDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);

//        DepartmentDto savedDepartmentDto = new DepartmentDto(
//                savedDepartment.getId(),
//                savedDepartment.getDepartmentName(),
//                savedDepartment.getDepartmentDescription(),
//                savedDepartment.getDepartmentCode()
//        );
    return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public List<DepartmentDto> saveDepartments(List<DepartmentDto> departmentDtos) {

        List<Department> departments = departmentDtos.stream().map(AutoDepartmentMapper.MAPPER::mapToDepartment).toList();

        List<Department> savedDepartments = departmentRepository.saveAll(departments);

        return savedDepartments.stream().map(AutoDepartmentMapper.MAPPER::mapToDepartmentDto).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Department department = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new ResourceNotFoundException("department", "departmentCode", departmentCode));

//        DepartmentDto departmentDto = new DepartmentDto(
//                department.getId(),
//                department.getDepartmentName(),
//                department.getDepartmentDescription(),
//                department.getDepartmentCode()
//        );
        return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);
    }

    public DepartmentDto getDepartmentById(Long id){
//        Department department = departmentRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("department", "departmentCode", id.toString())
//        );

        Department department = departmentRepository.findById(id).get();

        return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {

        List<Department> departments = departmentRepository.findAll();

       return departments.stream().map((AutoDepartmentMapper.MAPPER::mapToDepartmentDto)).collect(Collectors.toList());
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("department", "departmentCode", id.toString()));

        departmentRepository.deleteById(id);
    }

    @Override
    public void deleteAllDepartments() {
        departmentRepository.deleteAll();
    }


    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Department existingDepartment = departmentRepository.findById(departmentDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("department", "departmentCode", departmentDto.getDepartmentCode()));

        existingDepartment.setDepartmentName(departmentDto.getDepartmentName());
        existingDepartment.setDepartmentCode(departmentDto.getDepartmentCode());
        existingDepartment.setDepartmentDescription(departmentDto.getDepartmentDescription());

        Department updatedDepartment = departmentRepository.save(existingDepartment);

        return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(updatedDepartment);
    }
}
