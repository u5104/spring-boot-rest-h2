package com.example.demo.controllers;

import com.example.demo.entities.Account;
import com.example.demo.entities.Customer;
import com.example.demo.models.AccountModel;
import com.example.demo.models.CustomerModel;
import com.example.demo.services.IAccountService;
import com.example.demo.services.ICustomerService;
import com.example.demo.services.IQuotaService;
import com.example.demo.services.exceptions.AccountNotFoundException;
import com.example.demo.services.exceptions.CustomerNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gui/email")
@Validated
public class MyEmailGuiController {

    private final ICustomerService customerService;
    private final IAccountService accountService;
    private final IQuotaService quotaService;

    private ModelMapper modelMapper;

    @Autowired
    public MyEmailGuiController(ICustomerService customerService,
                                ModelMapper modelMapper,
                                IAccountService accountService,
                                IQuotaService quotaService){
        this.customerService = customerService;
        this.modelMapper = modelMapper;
        this.accountService = accountService;
        this.quotaService = quotaService;
    }

    @PostMapping("/addCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerModel> addCustomer(@RequestParam String first, @RequestParam String last) {
        Customer customer = new Customer();
        customer.setFirstName(first);
        customer.setLastName(last);
        return new ResponseEntity<>(modelMapper.map(customerService.save(customer), CustomerModel.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/listCustomers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<CustomerModel>> getCustomers() {
        final List<Customer> customers = customerService.getCustomers();
        return ResponseEntity.ok().body(customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerModel.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/findCustomer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<CustomerModel> findCustomerById(@PathVariable @Min(1) Integer id) throws CustomerNotFoundException {
        final Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok().body(modelMapper.map(customer, CustomerModel.class));
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<String> update(@Valid @RequestBody CustomerModel customer) {
        customerService.save(modelMapper.map(customer, Customer.class));
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PutMapping("/updateAccount")
    public ResponseEntity<String> update(@Valid @RequestBody AccountModel account) {
        accountService.save(modelMapper.map(account, Account.class));
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/addAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AccountModel> addAccount(@RequestParam Double quota, @RequestParam Double price) {
        Account account = new Account();
        account.setPrice(price);
        account.setCurrentQuota(quota);
        return new ResponseEntity<>(modelMapper.map(accountService.save(account), AccountModel.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/findAccount/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<AccountModel> findAccountById(@PathVariable @Min(1) Integer id) throws AccountNotFoundException {
        final Account account = accountService.getAccountById(id);
        return ResponseEntity.ok().body(modelMapper.map(account, AccountModel.class));
    }

    @GetMapping(value = "/availableQuotas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Double>> getAvailableQuotas() {
        final Iterable<Double> availableQuotas = quotaService.getAvailableQuotas();
        return ResponseEntity.ok().body(availableQuotas);
    }

}
