package com.codegym.model.dto;

import com.codegym.model.Drinks;
import com.codegym.model.Order;
import com.codegym.model.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {

    private long id ;
    private int quantity;
    private Long id_drink;
    private Long id_order;
    private BigDecimal price;
    private BigDecimal amount;


    public OrderDetail toOrderDetail(Drinks drinks, Order order){
        OrderDetail orderDetail = new OrderDetail() ;
        orderDetail.setId(id) ;
        orderDetail.setDrinks(drinks);
        orderDetail.setOrder(order);
        orderDetail.setAmount(amount);
        return orderDetail ;
    }
}
