package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DrinksController {

    @GetMapping("/home")
    public ModelAndView showMenu(){
        ModelAndView modelAndView = new ModelAndView("/index") ;
       return modelAndView ;
    }
}
