package com.codegym.model.dto;

import com.codegym.model.Desk;
import com.codegym.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private long id ;
    private Long idStaff ;
    private Long idDesk ;
    private String nameDesk ;
    private String createAtDay ;
    private String createAtTime ;


    public Order toOrder(Desk desk){
        Order order = new Order() ;
        order.setId(id);
        order.setCreate_by(idStaff);
        order.setDesk(desk);
        return order ;
    }
}
