package com.codegym.service.account;

import com.codegym.model.Account;
import com.codegym.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService{

    @Autowired
    private IAccountRepository accountRepository ;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findAllNotDeleted() {
        return accountRepository.findAllNotDeleted();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void remove(Long id) {
        Account account = accountRepository.findById(id).get() ;
        account.setDeleted(true);
        accountRepository.save(account) ;
    }
}
