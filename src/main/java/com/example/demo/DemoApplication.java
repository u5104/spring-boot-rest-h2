package com.example.demo;

import com.example.demo.entities.Account;
import com.example.demo.entities.Customer;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public DemoApplication(CustomerRepository customerRepository, AccountRepository accountRepository){
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    InitializingBean seedDatabase() {
        return () -> {
            Customer customer0 = new Customer();
            customer0.setFirstName("john");
            customer0.setLastName("jones");
            customerRepository.save(customer0);

            final Account account = new Account();
            account.setCustomer(customer0);
            account.setAccountType("FREE");
            account.setCurrentQuota("1.0");
            account.setPrice(0d);
            accountRepository.save(account);

            Customer customer1 = new Customer();
            customer1.setFirstName("james");
            customer1.setLastName("jameson");
            customerRepository.save(customer1);

            final Account account1 = new Account();
            account1.setCustomer(customer1);
            account1.setAccountType("PAID_01");
            account1.setCurrentQuota("10");
            account1.setPrice(9.99d);
            accountRepository.save(account1);
        };
    }
}
