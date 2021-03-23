package com.example.demo.controllers;

import com.example.demo.entities.Account;
import com.example.demo.entities.Customer;
import com.example.demo.services.IAccountService;
import com.example.demo.services.ICustomerService;
import com.example.demo.services.IQuotaService;
import com.example.demo.services.exceptions.CustomerNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gui/support")
@Validated
public class SupportToolsGuiController {

    private final ICustomerService customerService;
    private final IAccountService accountService;

    private ModelMapper modelMapper;

    @Autowired
    public SupportToolsGuiController(ICustomerService customerService,
                                     ModelMapper modelMapper,
                                     IAccountService accountService,
                                     IQuotaService quotaService) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
        this.accountService = accountService;
    }

    @GetMapping(value = "/findQuota/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Iterable<Double>> findCustomerById(@PathVariable @Min(1) Integer id) throws CustomerNotFoundException {
        final Customer customer = customerService.getCustomerById(id);
        final List<Double> quotas = customer.getAccounts().stream().map(Account::getCurrentQuota).collect(Collectors.toList());
        return ResponseEntity.ok().body(quotas);
    }
}
