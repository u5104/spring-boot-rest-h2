package com.example.demo.conf;

import com.example.demo.entities.Account;
import com.example.demo.entities.Customer;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder  implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public DatabaseSeeder(CustomerRepository customerRepository, AccountRepository accountRepository){
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) {
        logger.info("Seeding database ...");

        Customer customer0 = new Customer();
        customer0.setFirstName("john");
        customer0.setLastName("jones");
        customerRepository.save(customer0);

        final Account account = new Account();
        account.setCustomer(customer0);
        account.setCurrentQuota(1d);
        account.setPrice(4.99d);
        accountRepository.save(account);

        Customer customer1 = new Customer();
        customer1.setFirstName("james");
        customer1.setLastName("jameson");
        customerRepository.save(customer1);

        final Account account1 = new Account();
        account1.setCustomer(customer1);
        account1.setCurrentQuota(10d);
        account1.setPrice(9.99d);
        accountRepository.save(account1);

        logger.info("Seeding completed ...");
    }
}
