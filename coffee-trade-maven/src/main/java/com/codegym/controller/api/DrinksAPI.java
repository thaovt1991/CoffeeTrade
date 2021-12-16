package com.codegym.controller.api;

import com.codegym.model.Drinks;
import com.codegym.model.dto.DrinksDTO;
import com.codegym.service.drinks.IDrinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/drinks")
public class DrinksAPI {

    @Autowired
    private IDrinksService drinksService ;

//    @GetMapping
//    public ResponseEntity<Iterable<?>> findAll() {
//        try {
//            Iterable<DrinksDTO> drinksDTOS = drinksService.findAllCustomerDTO();
//
//            if (((List) customerDTOS).isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

//    @GetMapping
//    public ResponseEntity<Iterable<?>> findAll() {
//        try {
//            Iterable<Drinks> drinksDTOS = drinksService.findAll();
//
//            if (((List) drinksDTOS).isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
}
