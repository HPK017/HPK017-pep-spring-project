package com.example.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import java.util.List;

public class AccountService {
     
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Integer account_id){
        return accountRepository.findById(account_id);
    }

    public Account createAccount(Account account_id){
        return accountRepository.save(account_id);
    }

    public void deleteAccount(Integer account_id){
        accountRepository.deleteById(account_id);
    }

    public Account updateAccount(Integer account_id, Account account){
        if(accountRepository.existsById(account_id)){
            account.setAccount_id(account_id);
            return accountRepository.save(account);
        }
        else{
            throw new IllegalArgumentException("Account with id"+ account_id +"does not exist");
        }
    }
}
