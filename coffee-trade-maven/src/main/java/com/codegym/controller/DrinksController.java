package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DrinksController {

    @GetMapping("/home")
    public ModelAndView showHome(){
        return new ModelAndView("/action/home");
    }

    @GetMapping("/menu")
    public ModelAndView showMenu(){
        return new ModelAndView("/action/menu");
    }


}
