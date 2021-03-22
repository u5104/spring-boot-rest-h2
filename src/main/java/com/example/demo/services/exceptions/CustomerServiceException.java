package com.example.demo.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomerServiceException extends Exception {
    public CustomerServiceException(Exception e) {
        super(e);
    }
}
