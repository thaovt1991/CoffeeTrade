package com.codegym.controller.api;

import com.codegym.model.Bill;
import com.codegym.model.Desk;
import com.codegym.model.Order;
import com.codegym.model.dto.BillDTO;
import com.codegym.service.bill.IBillService;
import com.codegym.service.desk.IDeskService;
import com.codegym.service.order.IOrderService;
import com.codegym.service.order_detail.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bill")
public class BillControllerAPI {

    @Autowired
    private IBillService billService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private IDeskService deskService ;

    @PostMapping("/create")
    public Bill createBill(@RequestBody BillDTO billDTO) {
        Long idOrder = billDTO.getIdOrder();
        Order order = orderService.findById(idOrder).get();
        Bill bill = billDTO.toBill(order);
//        try {
            billService.save(bill);
            order.setPaymented(true);
            orderService.save(order) ;
            Desk desk = order.getDesk() ;
            desk.setEmpty(true);
            deskService.save(desk) ;
//        } catch (Exception e) {
//            bill = new Bill();
//        }

        return bill;
    }

    @GetMapping("/getallbill")
    public Iterable<Bill> getABill() {
        return billService.findAllNotDeleted();
    }

}
