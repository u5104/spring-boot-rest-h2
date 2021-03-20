package com.example.demo.controllers;

import com.example.demo.entities.Account;
import com.example.demo.entities.Customer;
import com.example.demo.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gui")
public class EmailGuiController {

    private final ICustomerService customerService;

    @Autowired
    public EmailGuiController(ICustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping(value = "/getAccounts/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Account>> getAccountsForCustomer(@PathVariable Integer customerId) {
        return ResponseEntity.ok().body(customerService.getCustomerById(customerId).getAccounts());
    }
}
