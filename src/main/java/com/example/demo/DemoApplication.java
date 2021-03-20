package com.example.demo;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    private final CustomerRepository customerRepository;

    @Autowired
    public DemoApplication(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            Customer customer0 = new Customer();
            customer0.setFirstName("john");
            customer0.setLastName("jones");
            customerRepository.save(customer0);

            Customer customer1 = new Customer();
            customer1.setFirstName("james");
            customer1.setLastName("jameson");
            customerRepository.save(customer1);
        };
    }
}
