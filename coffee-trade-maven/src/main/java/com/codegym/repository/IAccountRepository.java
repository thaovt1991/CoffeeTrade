package com.codegym.repository;

import com.codegym.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "select a from Account a where a.isDeleted = false")
    List<Account> findAllNotDeleted() ;
}
