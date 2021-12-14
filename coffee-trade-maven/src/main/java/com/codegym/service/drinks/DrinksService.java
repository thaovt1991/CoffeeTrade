package com.codegym.service.drinks;

import com.codegym.model.Drinks;
import com.codegym.repository.IDrinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DrinksService implements IDrinksService{

    @Autowired
    private IDrinksRepository drinksRepository ;

    @Override
    public List<Drinks> findAll() {
        return drinksRepository.findAll();
    }

    @Override
    public List<Drinks> findAllNotDeleted() {
        return drinksRepository.findAllNotDeleted();
    }

    @Override
    public Optional<Drinks> findById(Long id) {
        return drinksRepository.findById(id);
    }

    @Override
    public Drinks save(Drinks drinks) {
        return drinksRepository.save(drinks);
    }

    @Override
    public void remove(Long id) {
        Drinks drinks = findById(id).get() ;
        drinks.setDeleted(true);
        drinksRepository.save(drinks) ;
    }
}
