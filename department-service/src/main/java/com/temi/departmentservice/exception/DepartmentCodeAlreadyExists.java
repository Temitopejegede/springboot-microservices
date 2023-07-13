package com.temi.departmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DepartmentCodeAlreadyExists extends RuntimeException{

    private String message;

    public DepartmentCodeAlreadyExists(String message) {
        super(message);
    }
}
