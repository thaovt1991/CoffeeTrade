package com.codegym.controller.api;

import com.codegym.model.Staff;
import com.codegym.service.staff.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff")
public class StaffControllerAPI {

    @Autowired
    private IStaffService staffService ;

    @GetMapping("/{id}")
    public Staff staffById(@PathVariable Long id){
       Staff staff =  staffService.findById(id).get() ;
        return staff ;
    }
}
