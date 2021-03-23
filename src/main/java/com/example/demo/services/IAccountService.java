package com.example.demo.services;

import com.example.demo.entities.Account;

import java.util.List;

public interface IAccountService {
    Account save(Account customer);

    void delete(Integer id);

    List<Account> getAccounts();

    Account getAccountById(Integer id);
}
