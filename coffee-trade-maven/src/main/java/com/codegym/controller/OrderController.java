package com.codegym.controller;

import com.codegym.model.Desk;
import com.codegym.model.Drinks;
import com.codegym.model.Staff;
import com.codegym.model.Type;
import com.codegym.service.desk.IDeskService;
import com.codegym.service.drinks.IDrinksService;
import com.codegym.service.staff.IStaffService;
import com.codegym.service.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private IDeskService deskService ;

    @Autowired
    private ITypeService typeService ;

    @Autowired
    private IDrinksService drinksService ;

    @Autowired
    private IStaffService staffService ;

    @GetMapping("/sell")
    public ModelAndView showDesk(){
        ModelAndView modelAndView = new ModelAndView("/action/sell") ;
        List<Desk> listDesks = deskService.findAllNotDeleted() ;
        List<Type> typeList = typeService.findAllNotDeleted() ;
        List<Drinks> listDrinksAll = drinksService.findAllNotDeleted();
        List<Desk> deskListNotEmpty = deskService.findAllByDeletedIsFalseAndEmptyIsFalse() ;
        List<Staff> staffList = staffService.findAllNotDeleted() ;
        List<Desk> listDesksEmpty = deskService.findAllNotDeletedAndEmpty() ;


        modelAndView.addObject("listDesks",listDesks) ;
        modelAndView.addObject("deskListNotEmpty",deskListNotEmpty) ;
        modelAndView.addObject("typeList",typeList) ;
        modelAndView.addObject("listDrinksAll",listDrinksAll);
        modelAndView.addObject("staffList",staffList) ;
        modelAndView.addObject("listDesksEmpty",listDesksEmpty) ;
        return modelAndView ;
    }
}
