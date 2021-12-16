package com.codegym.controller;

import com.codegym.model.Desk;
import com.codegym.model.Drinks;
import com.codegym.model.Type;
import com.codegym.service.desk.IDeskService;
import com.codegym.service.drinks.IDrinksService;
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

    @GetMapping("/sell")
    public ModelAndView showDesk(){
        ModelAndView modelAndView = new ModelAndView("/action/sell") ;
        List<Desk> listDesks = deskService.findAllNotDeleted() ;
        List<Type> typeList = typeService.findAllNotDeleted() ;
        List<Drinks> listDrinksAll = drinksService.findAllNotDeleted();
        modelAndView.addObject("listDesks",listDesks) ;
        modelAndView.addObject("typeList",typeList) ;
        modelAndView.addObject("listDrinksAll",listDrinksAll);
        return modelAndView ;
    }
}
