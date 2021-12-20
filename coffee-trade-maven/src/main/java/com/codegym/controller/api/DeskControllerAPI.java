package com.codegym.controller.api;

import com.codegym.model.Desk;
import com.codegym.service.desk.IDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/desk")
public class DeskControllerAPI {

    @Autowired
    private IDeskService deskService;

    @GetMapping("/{id}")
    public ResponseEntity<Desk> findCustomerById(@PathVariable Long id) {
        Optional<Desk> deskOptional = deskService.findById(id);
        if (!deskOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Desk desk = deskOptional.get();
        return new ResponseEntity<>(desk, HttpStatus.OK);
    }


    @PostMapping("/create")
    public Desk createTable(@RequestBody Desk desk) {
        return deskService.save(desk);
    }

    @GetMapping("/getalldesk")
    public List<Desk> getAllDesk() {
        return deskService.findAllNotDeleted();
    }

    @GetMapping("/getalldeskempty")
    public List<Desk> getAllDeskEmpty() {
        return deskService.findAllNotDeletedAndEmpty();
    }

    @GetMapping("/getalldesknotempty")
    public List<Desk> getAllDeskNotEmpty() {
        return deskService.findAllNotDeletedAndNotEmpty();
    }

    @GetMapping("/getDeskById/{id}")
    public Desk getDeskById(@PathVariable Long id) {
        return deskService.findById(id).get();
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        deskService.remove(id);

        Optional<Desk> desk = deskService.findById(id);
        if (desk.isPresent()) {
            return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

//    @PostMapping("/edit")
//    public Desk editDesk(@RequestBody Desk desk) {
//        return deskService.save(desk);
//    }
//
//    @PutMapping("/update/{id}")
//    public Desk updateDesk(@PathVariable Long id) {
//        Desk desk = deskService.findById(id).get();
//        Desk newDesk = new Desk();
//        newDesk.setId(desk.getId());
//        newDesk.setName(desk.getName());
//        return deskService.save(newDesk);
//    }

    @PutMapping("/update")
    public Desk updateDesk(@RequestBody Desk desk) {
        Long id = desk.getId() ;
        Optional<Desk> deskOptional = deskService.findById(id);
        desk.setId(deskOptional.get().getId());
       return deskService.save(desk) ;
    }

}

