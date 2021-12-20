package com.codegym.model;

import com.codegym.model.dto.OrderDetailDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date created_at = new Date();

    private Long create_by;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date update_at = new Date();

    private Long update_by;

    @ManyToOne
    @JoinColumn(name = "drinks_id")
    private Drinks drinks;

    @Min(1)
    private int quantity ;

    @Digits(integer = 12, fraction = 0 )
    private BigDecimal amount ;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    private boolean isDeleted = false;

    public OrderDetailDTO toOrderDetailDTO(){
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO() ;
        orderDetailDTO.setId(id) ;
        orderDetailDTO.setName(drinks.getName());
        orderDetailDTO.setId_order(order.getId());
        orderDetailDTO.setId_drink(drinks.getId());
        orderDetailDTO.setPrice(drinks.getPrice());
        orderDetailDTO.setQuantity(quantity);
        BigDecimal amount = drinks.getPrice().multiply(BigDecimal.valueOf(quantity)) ;
        orderDetailDTO.setAmount(amount);
        return orderDetailDTO ;
    }

}
