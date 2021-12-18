package com.codegym.controller.api;

import com.codegym.model.Drinks;
import com.codegym.model.dto.DrinksDTO;
import com.codegym.service.drinks.IDrinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/drinks")
public class DrinksControllerAPI {

    @Autowired
    private IDrinksService drinksService ;

    @GetMapping
    public ResponseEntity<Iterable<Drinks>> findAll() {
        try {
            Iterable<Drinks> drinksList = drinksService.findAllNotDeleted();

            if (((List) drinksList).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(drinksList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Drinks> findCustomerById(@PathVariable Long id) {
        Optional<Drinks> drinksOptional = drinksService.findById(id);
        if (!drinksOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Drinks drinks = drinksOptional.get();
        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }
}
