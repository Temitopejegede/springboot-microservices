package com.temi.employeeservice.mapper;

import com.temi.employeeservice.dto.EmployeeDto;
import com.temi.employeeservice.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoEmployeeMapper {

    AutoEmployeeMapper MAPPER = Mappers.getMapper(AutoEmployeeMapper.class);

    @Mapping(source = "departmentCode", target = "departmentCode")
    EmployeeDto mapToEmployeeDto(Employee employee);

    @Mapping(source = "departmentCode", target = "departmentCode")
    Employee mapToEmployee(EmployeeDto employeeDto);
}
