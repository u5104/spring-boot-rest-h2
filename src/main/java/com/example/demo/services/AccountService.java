package com.example.demo.services;

import com.example.demo.entities.Account;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.services.exceptions.AccountNotFoundException;
import com.example.demo.services.exceptions.NoAccountDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account save(Account customer) {
        return accountRepository.save(customer);
    }

    @Override
    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<Account> getAccounts() {
        final List<Account> all = (List<Account>) accountRepository.findAll();
        if(all.isEmpty())
            throw new NoAccountDataFoundException("Account data not found.");
        return all;
    }

    @Override
    public Account getAccountById(Integer id) {
        Optional<Account> optionalCustomer = accountRepository.findById(id);
        return optionalCustomer.orElseThrow(() -> new AccountNotFoundException("Couldn't find an Account with id: " + id));
    }
}
