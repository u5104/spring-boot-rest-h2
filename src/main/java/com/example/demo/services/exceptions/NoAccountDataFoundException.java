package com.example.demo.services.exceptions;

public class NoAccountDataFoundException extends RuntimeException {
    public NoAccountDataFoundException(String message) {
        super(message);
    }
}
