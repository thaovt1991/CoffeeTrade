package com.codegym.controller.api;

import com.codegym.model.Order;
import com.codegym.service.desk.IDeskService;
import com.codegym.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderControllerAPI {

    @Autowired
    private IOrderService orderService ;

    @PostMapping("/create")
    public Order createOrder (@RequestBody Order order) {
        return orderService.save(order);
    }

    @GetMapping("/getorderbydeskid/{id}")
    public Order getOrderByDeskid (@PathVariable Long id) {
        return orderService.getOrderByDeskId(id);
    }

}
