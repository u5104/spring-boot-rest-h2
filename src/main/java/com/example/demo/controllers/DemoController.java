package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.models.CustomerModel;
import com.example.demo.services.CustomerNotFoundException;
import com.example.demo.services.ICustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class DemoController {

    private final ICustomerService customerService;

    private ModelMapper modelMapper;

    @Autowired
    public DemoController(ICustomerService customerService, ModelMapper modelMapper){
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> addCustomer(@RequestParam String first, @RequestParam String last) {
        Customer customer = new Customer();
        customer.setFirstName(first);
        customer.setLastName(last);
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.CREATED);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<CustomerModel>> getCustomers() {
        final List<Customer> customers = customerService.getCustomers();
        return ResponseEntity.ok().body(customers.stream()
                .map(customer -> {return modelMapper.map(customer, CustomerModel.class);})
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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
