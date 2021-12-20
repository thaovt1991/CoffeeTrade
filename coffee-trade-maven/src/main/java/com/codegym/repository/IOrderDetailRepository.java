package com.codegym.repository;

import com.codegym.model.CarriedAway;
import com.codegym.model.OrderDetail;
import com.codegym.model.dto.IOrderDetailSumDTO;
import com.codegym.model.dto.OrderDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query(value = "select o from OrderDetail o where o.isDeleted = false")
    List<OrderDetail> findAllNotDeleted();

    @Query("SELECT NEW com.codegym.model.dto.OrderDetailDTO (o.id,o.drinks.name, o.quantity, o.drinks.id, o.order.id,o.drinks.price,o.amount) FROM OrderDetail o WHERE o.isDeleted = false ")
    List<OrderDetailDTO> findAllOrderDetailDTO() ;

    @Query(value = "select o from OrderDetail o where o.isDeleted = false and o.order.id = ?1")
    List<OrderDetail> findOrderDetailByOrderId(Long id);

    @Query("SELECT " +
            "od.drinks.name AS drinksName, " +
            "od.drinks.price AS price, " +
            "od.quantity AS quantity, " +
            "od.amount AS amount " +
            "FROM OrderDetail AS od " +
            "WHERE od.order.id = ?1 ")
    List<IOrderDetailSumDTO> getAllIOrderDetailSumDTOByOrderId(Long id);
}
