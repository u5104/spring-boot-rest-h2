package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.exceptions.CustomerNotFoundException;
import com.example.demo.services.exceptions.NoCustomerDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getCustomers() {
        final List<Customer> all = (List<Customer>) customerRepository.findAll();
        if(all.isEmpty())
            throw new NoCustomerDataFoundException("Customer data not found.");
        return all;
    }

    @Override
    public Customer getCustomerById(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElseThrow(() -> new CustomerNotFoundException("Couldn't find a Customer with id: " + id));
    }
}
