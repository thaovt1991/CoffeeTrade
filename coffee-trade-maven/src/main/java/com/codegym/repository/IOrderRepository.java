package com.codegym.repository;

import com.codegym.model.Order;
import com.codegym.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository< Order, Long> {

    @Query(value = "select o from Order o where o.isDeleted = false")
    List<Order> findAllNotDeleted() ;


    @Query(value = "select o from Order o where o.isDeleted = false and o.desk.id = ?1")
    Order getOrderByDeskId(Long id) ;

    @Query(value = "select o from Order o where o.isDeleted = false and  o.isPaymented = false and o.desk.id = ?1")
    Order getOrderByDeskIdNotPayment(Long id) ;


}
