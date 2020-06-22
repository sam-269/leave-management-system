package com.projects.leavemanagementsystem.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EmployeeIdNotFoundException.class)
    public ResponseEntity<Object> exception(EmployeeIdNotFoundException exception) {
        return new ResponseEntity<>("Such employee id does not exist", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EndDateGreaterException.class)
    public ResponseEntity<Object> exception(EndDateGreaterException exception) {
        return new ResponseEntity<>("End date cannot be greater than start date", HttpStatus.NOT_FOUND);
    }
}
