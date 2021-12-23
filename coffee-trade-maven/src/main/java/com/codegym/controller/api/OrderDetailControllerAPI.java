package com.codegym.controller.api;

import com.codegym.model.Desk;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void createOderDetails(@RequestBody OrderDetailDTO orderDetailDTO){
        OrderDetail orderDetail = new OrderDetail() ;
        Order order = orderService.findById(orderDetailDTO.getId_order()).get();
        Drinks drinks = drinksService.findById(orderDetailDTO.getId_drink()).get();
        orderDetail.setOrder(order);
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setDrinks(drinks);
        BigDecimal amountDTO = drinks.getPrice().multiply(BigDecimal.valueOf(orderDetailDTO.getQuantity()));
        orderDetail.setAmount(orderDetailDTO.getAmount());
        orderDetailService.save(orderDetail) ;

//        for (int i = 0; i < orderDetailDTO.length; i++) {
//
//        }

    }

    @PutMapping("/update")
    public OrderDetail updateOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        Long id = orderDetailDTO.getId() ;

        Order order = orderService.findById(orderDetailDTO.getId_order()).get();
        Drinks drinks = drinksService.findById(orderDetailDTO.getId_drink()).get();
        OrderDetail orderDetail = orderDetailDTO.toOrderDetail(drinks,order) ;
        Optional<OrderDetail> orderDetailOptional = orderDetailService.findById(id);

        BigDecimal amountDTO = drinks.getPrice().multiply(BigDecimal.valueOf(orderDetailDTO.getQuantity()));
        orderDetail.setAmount(orderDetailDTO.getAmount());
        orderDetail.setId(orderDetailOptional.get().getId());
        return orderDetailService.save(orderDetail) ;
    }

    @GetMapping("/orderdetailofdeskid/{id}")
    public List<OrderDetailDTO> getOrderDetailOfDeskid (@PathVariable Long id) {
        Order order = orderService.getOrderByDeskIdNotPayment(id);

        List<OrderDetail> orderDetailList =  orderDetailService.findOrderDetailByOrderId(order.getId());
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>() ;
        for (OrderDetail od : orderDetailList ){
            orderDetailDTOS.add(od.toOrderDetailDTO()) ;
        }
      return orderDetailDTOS ;
    }

    @GetMapping("/order-detail-of-deskid/{id}")
    public List<IOrderDetailSumDTO> getAllIOrderDetailSumDTOByOrderId (@PathVariable Long id) {
        Order order = orderService.getOrderByDeskIdNotPayment(id);
        return orderDetailService.getAllIOrderDetailSumDTOByOrderId(order.getId());
    }

    @GetMapping("/getorderdetailbyorderid/{id}")
    public List<OrderDetail> getOrderDetailByOrderIdOrderDetails (@PathVariable Long id) {
        return orderDetailService.findOrderDetailByOrderId(id);
    }

    @GetMapping("/getallorderdetail")
    public Iterable<OrderDetail> getAllOrderDetail () {
        return orderDetailService.findAllNotDeleted();
    }
}
