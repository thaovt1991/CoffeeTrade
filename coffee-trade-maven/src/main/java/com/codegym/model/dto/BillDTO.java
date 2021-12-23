package com.codegym.model.dto;

import com.codegym.model.Bill;
import com.codegym.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
    private long id ;
    private Long idOrder ;
    private String nameDesk ;
    private String timeBegin ;
    private String timeEnd ;
    private Long idStaff ;
    private String nameStaff ;
    private BigDecimal amountOrder ;
    private int promotion ;
    private int  surcharge ;
    private int tax ;
    private BigDecimal totalMoney ;

    public BillDTO(Long idOrder, Long idStaff, int promotion, int surcharge, int tax, BigDecimal totalMoney) {
        this.idOrder = idOrder;
        this.idStaff = idStaff;
        this.promotion = promotion;
        this.surcharge = surcharge;
        this.tax = tax;
        this.totalMoney = totalMoney;
    }

    public Bill toBill(Order order){
       Bill bill = new Bill() ;
       bill.setId(id);
       bill.setOrder(order);
       bill.setCreate_by(idStaff);
       bill.setPromotion(promotion) ;
       bill.setSurcharge(surcharge) ;
       bill.setTotalMoney(totalMoney);
       return bill ;
   }
}
