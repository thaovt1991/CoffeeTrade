package com.codegym.repository;

import com.codegym.model.CarriedAway;
import com.codegym.model.Drinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface IDrinksRepository extends JpaRepository<Drinks, Long> {

    @Query(value = "select d from Drinks d where d.isDeleted = false")
    List<Drinks> findAllNotDeleted() ;
}
