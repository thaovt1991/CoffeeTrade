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
    private String name ;
    private int quantity;
    private Long id_drink;
    private Long id_order;

    private BigDecimal price;
    private BigDecimal amount;

    public OrderDetailDTO(int quantity, Long id_drink, Long id_order) {
        this.quantity = quantity;
        this.id_drink = id_drink;
        this.id_order = id_order;
    }

    public OrderDetail toOrderDetail(Drinks drinks, Order order){
        OrderDetail orderDetail = new OrderDetail() ;
        orderDetail.setId(id) ;
        orderDetail.setQuantity(quantity); ;
        orderDetail.setDrinks(drinks);
        orderDetail.setOrder(order);
        return orderDetail ;
    }
}
