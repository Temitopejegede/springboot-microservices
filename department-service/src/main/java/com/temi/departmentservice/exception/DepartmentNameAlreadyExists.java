package com.temi.departmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DepartmentNameAlreadyExists extends RuntimeException {

    private String message;

    public DepartmentNameAlreadyExists(String message) {
        super(message);
    }
}
