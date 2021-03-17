package com.example.demo.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class CustomerServiceErrorAdvice {
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return error(INTERNAL_SERVER_ERROR, e);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({CustomerNotFoundException.class, NullPointerException.class})
    public ResponseEntity<String> handleNotFoundException(CustomerNotFoundException e) {
        return error(NOT_FOUND, e);
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({CustomerServiceException.class})
    public ResponseEntity<String> handleDogsServiceException(CustomerServiceException e){
        return error(INTERNAL_SERVER_ERROR, e);
    }
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler({DogsServiceValidationException.class})
//    public void handle(DogsServiceValidationException e) {}

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
