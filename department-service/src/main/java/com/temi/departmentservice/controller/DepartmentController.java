package com.temi.departmentservice.controller;

import com.temi.departmentservice.dto.DepartmentDto;
import com.temi.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@AllArgsConstructor
@RequestMapping("api/departments")

public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //save department rest api

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(
            @RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    //get department rest api

    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode){
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto department,
                                                          @PathVariable("id") Long departmentId){
        department.setId(departmentId);
        DepartmentDto updatedDepartment = departmentService.updateDepartment(department);

        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartment(departmentId);

        return new ResponseEntity<>("Department successfully deleted", HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteAllDepartments(){
        departmentService.deleteAllDepartments();

        return new ResponseEntity<>("All departments successfully deleted", HttpStatus.OK);
    }
}
