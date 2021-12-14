package com.codegym.repository;

import com.codegym.model.CarriedAway;
import com.codegym.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query(value = "select o from OrderDetail o where o.isDeleted = false")
    List<OrderDetail> findAllNotDeleted() ;
}
