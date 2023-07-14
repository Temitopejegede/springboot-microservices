package com.temi.departmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DepartmentNameAlreadyExistsException extends RuntimeException {

    private String message;

    public DepartmentNameAlreadyExistsException(String message) {
        super(message);
    }
}
