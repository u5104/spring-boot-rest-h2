package com.example.demo.services;

import com.example.demo.Customer;

import java.util.List;

public interface ICustomerService {
    Customer save(Customer customer);

    void delete(Integer id);

    List<Customer> getCustomers();

    Customer getCustomerById(Integer id) throws CustomerNotFoundException;
}