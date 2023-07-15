package com.temi.employeeservice.controller;

import com.temi.employeeservice.dto.ApiResponseDto;
import com.temi.employeeservice.dto.EmployeeDto;
import com.temi.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
//@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //save employee rest api
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(
            @RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<List<EmployeeDto>> saveEmployees(@RequestBody List<EmployeeDto> employeeDtoList){
        List<EmployeeDto> employeeDtos = employeeService.saveEmployees(employeeDtoList);
        return new ResponseEntity<>(employeeDtos, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> getEmployeeById(
            @PathVariable("id") Long employeeId){
        ApiResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok(allEmployees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto,
                                                      @PathVariable("id") Long employeeId){
        employeeDto.setId(employeeId);
        EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeDto);

        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee Successfully Deleted");
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteAllEmployees(){
        employeeService.deleteAllEmployees();
        return ResponseEntity.ok("All employees successfully deleted");
    }
}
