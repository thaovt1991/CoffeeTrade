package com.codegym.model;

import com.codegym.model.dto.BillDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @OneToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date created_at = new Date();

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date update_at = new Date();

    private Long update_by;


    private Long create_by;

    private int promotion ;

    private int  surcharge ;

    private int tax ;

    @Digits(integer = 12, fraction = 0 )
    private BigDecimal totalMoney ;

    private boolean isDeleted = false;


    public BillDTO billDTO(){
        BillDTO billDTO = new BillDTO() ;
        billDTO.setId(id);
        billDTO.setIdOrder(order.getId());
        billDTO.setNameDesk(order.getDesk().getName());
        billDTO.setTimeBegin(order.getCreated_at().toString());
        billDTO.setTimeEnd(created_at.toString());
        billDTO.setIdStaff(order.getCreate_by()) ;
        billDTO.setPromotion(promotion);
        billDTO.setSurcharge(surcharge);
        billDTO.setTax(tax);
        billDTO.setTotalMoney(totalMoney);
        return billDTO ;
    }
}
