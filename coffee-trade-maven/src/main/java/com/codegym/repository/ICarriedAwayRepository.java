package com.codegym.repository;

import com.codegym.model.Bill;
import com.codegym.model.CarriedAway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICarriedAwayRepository extends JpaRepository<CarriedAway, Long> {

    @Query(value = "select c from CarriedAway c where c.isDeleted = false")
    List<CarriedAway> findAllNotDeleted() ;
}
