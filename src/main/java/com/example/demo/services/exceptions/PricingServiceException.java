package com.example.demo.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PricingServiceException extends RuntimeException  {
    public PricingServiceException(String message) {
        super(message);
    }
}
