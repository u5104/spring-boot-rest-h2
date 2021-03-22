package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.services.exceptions.CustomerNotFoundException;
import com.example.demo.services.exceptions.CustomerServiceException;

import java.util.List;

public interface ICustomerService {
    Customer save(Customer customer);

    void delete(Integer id);

    List<Customer> getCustomers();

    Customer getCustomerById(Integer id);
}
