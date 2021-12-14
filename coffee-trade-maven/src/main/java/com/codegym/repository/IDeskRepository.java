package com.codegym.repository;

import com.codegym.model.CarriedAway;
import com.codegym.model.Desk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDeskRepository extends JpaRepository<Desk, Long> {

    @Query(value = "select d from Desk d where d.isDeleted = false")
    List<Desk> findAllNotDeleted() ;
}
