package com.example.demo.controllers;

import com.example.demo.Customer;
import com.example.demo.services.CustomerNotFoundException;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class DemoController {

    private final CustomerService customerService;

    @Autowired
    public DemoController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addCustomer(@RequestParam String first, @RequestParam String last) {
        Customer customer = new Customer();
        customer.setFirstName(first);
        customer.setLastName(last);
        customerService.save(customer);
        return "Added new customer to repo!";
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<Customer>> getCustomers() {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Customer findCustomerById(@PathVariable Integer id) throws CustomerNotFoundException {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Customer customer) {
        customerService.save(customer);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
