package com.temi.employeeservice.service.impl;

import com.temi.employeeservice.dto.EmployeeDto;
import com.temi.employeeservice.entity.Employee;
import com.temi.employeeservice.exceptions.EmailAlreadyExistsException;
import com.temi.employeeservice.exceptions.ResourceNotFoundException;
import com.temi.employeeservice.mapper.AutoEmployeeMapper;
import com.temi.employeeservice.repository.EmployeeRepository;
import com.temi.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
//        Employee employee = new Employee(
//                employeeDto.getId(),
//                employeeDto.getFirstName(),
//                employeeDto.getLastName(),
//                employeeDto.getEmail()
//        );
        Optional<Employee> optionalUser = employeeRepository.findByEmail(employeeDto.getEmail());

        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email already exists for a user");
        }

        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
//        EmployeeDto savedEmployeeDto = new EmployeeDto(
//                savedEmployee.getId(),
//                savedEmployee.getFirstName(),
//                savedEmployee.getLastName(),
//                savedEmployee.getEmail()
//        );
        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);

    }

    @Override
    public List<EmployeeDto> saveEmployees(List<EmployeeDto> employeeDtos) {
        List<Employee> employees = employeeDtos.stream().map(AutoEmployeeMapper.MAPPER::mapToEmployee).toList();

        List<Employee> savedEmployees = employeeRepository.saveAll(employees);

        return savedEmployees.stream().map(AutoEmployeeMapper.MAPPER::mapToEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );
//        EmployeeDto employeeDto = new EmployeeDto(
//                employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail()
//        );\
        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map(AutoEmployeeMapper.MAPPER::mapToEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepository.findById(employeeDto.getId()).orElseThrow();

        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setEmail(employeeDto.getEmail());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id).orElseThrow();
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }


}
