package com.codegym.controller.api;

import com.codegym.model.Bill;
import com.codegym.service.bill.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bill")
public class BillControllerAPI {

    @Autowired
    private IBillService billService ;

    @PostMapping("/create")
    public Bill createBill (@RequestBody Bill bill) {
        return billService.save(bill);
    }

    @GetMapping("/getallbill")
    public Iterable<Bill> getABill () {
        return billService.findAllNotDeleted();
    }

}
