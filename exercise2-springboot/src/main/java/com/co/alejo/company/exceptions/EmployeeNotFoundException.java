package com.co.alejo.company.exceptions;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
