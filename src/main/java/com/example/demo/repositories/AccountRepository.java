package com.example.demo.repositories;

import com.example.demo.entities.Account;
import com.example.demo.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    Account findAccountById(Integer id);
}
