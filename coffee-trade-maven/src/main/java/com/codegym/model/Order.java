package com.codegym.model;


import com.codegym.model.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date created_at = new Date();

    //    @ManyToOne
//    @JoinColumn(name = "create_by")
//    private Staff staff;
    private Long create_by;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date update_at = new Date();

    //    @ManyToOne
//    @JoinColumn(name = "update_by")
//    private Staff staff_up;
    private Long update_by;

    private boolean isDeleted = false;

    private boolean isPaymented = false;

    @JsonIgnore
    @OneToMany(targetEntity = OrderDetail.class, mappedBy = "order")
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "desk_id")
    private Desk desk;

    @ManyToOne
    @JoinColumn(name = "carried_away_id")
    private CarriedAway carriedAway;

    @OneToOne
    @JoinColumn(name = "bill_id")
    @JsonIgnore
    private Bill bill;

    public OrderDTO toOrderDTO(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(id);
        orderDTO.setIdStaff(getCreate_by());
        orderDTO.setIdDesk(desk.getId());
        orderDTO.setNameDesk(desk.getName());
        String createAt = getCreated_at().toString() ;
        int index = createAt.indexOf(" ");
        int index_last = createAt.indexOf(".");
        orderDTO.setCreateAtDay(createAt.substring(0,index));
        orderDTO.setCreateAtTime(createAt.substring(index,index_last));

        return orderDTO;
    }

}
