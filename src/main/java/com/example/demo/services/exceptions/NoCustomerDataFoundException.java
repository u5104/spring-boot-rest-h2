package com.example.demo.services.exceptions;

public class NoCustomerDataFoundException extends RuntimeException  {
    public NoCustomerDataFoundException(String message) {
        super(message);
    }
}
