package com.codegym.repository;

import com.codegym.model.Account;
import com.codegym.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Long> {

    @Query(value = "select b from Bill b where b.isDeleted = false")
    List<Bill> findAllNotDeleted() ;
}
