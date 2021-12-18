package com.codegym.controller.api;

import com.codegym.model.Drinks;
import com.codegym.model.Order;
import com.codegym.model.OrderDetail;
import com.codegym.model.dto.IOrderDetailSumDTO;
import com.codegym.model.dto.OrderDetailDTO;
import com.codegym.service.drinks.IDrinksService;
import com.codegym.service.order.IOrderService;
import com.codegym.service.order_detail.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order_detail")
public class OrderDetailControllerAPI {

    @Autowired
    public IOrderDetailService orderDetailService ;

    @Autowired
    public IOrderService orderService ;

    @Autowired
    public IDrinksService drinksService ;

    @PostMapping("/create")
    public OrderDetail createOderDetails(@RequestBody OrderDetailDTO orderDetailDTO){
        OrderDetail orderDetail = new OrderDetail() ;
        Order order = orderService.findById(orderDetailDTO.getId_order()).get();
        Drinks drinks = drinksService.findById(orderDetailDTO.getId_drink()).get();
        orderDetail.setOrder(order);
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setDrinks(drinks);
        orderDetail.setAmount(orderDetailDTO.getAmount());
        return orderDetailService.save(orderDetail) ;
    }

    @GetMapping("/orderdetail/orderdetailofdeskid/{id}")
    public List<OrderDetail> getOrderDetailOfDeskid (@PathVariable Long id) {
        Order order = orderService.getOrderByDeskId(id);
        return orderDetailService.findOrderDetailByOrderId(order.getId());
    }

    @GetMapping("/orderdetail/order-detail-of-deskid/{id}")
    public List<IOrderDetailSumDTO> getAllIOrderDetailSumDTOByOrderId (@PathVariable Long id) {
        Order order = orderService.getOrderByDeskId(id);
        return orderDetailService.getAllIOrderDetailSumDTOByOrderId(order.getId());
    }

    @GetMapping("/orderdetail/getorderdetailbyorderid/{id}")
    public List<OrderDetail> getOrderDetailByOrderIdOrderDetails (@PathVariable Long id) {
        return orderDetailService.findOrderDetailByOrderId(id);
    }

    @GetMapping("/orderdetail/getallorderdetail")
    public Iterable<OrderDetail> getAllOrderDetail () {
        return orderDetailService.findAllNotDeleted();
    }
}
