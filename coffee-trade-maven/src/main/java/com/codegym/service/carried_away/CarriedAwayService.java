package com.codegym.service.carried_away;

import com.codegym.model.CarriedAway;
import com.codegym.repository.ICarriedAwayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarriedAwayService implements ICarriedAwayService {

    @Autowired
    private ICarriedAwayRepository carriedAwayRepository ;
    @Override
    public List<CarriedAway> findAll() {
        return carriedAwayRepository.findAll();
    }

    @Override
    public List<CarriedAway> findAllNotDeleted() {
        return carriedAwayRepository.findAllNotDeleted();
    }

    @Override
    public Optional<CarriedAway> findById(Long id) {
        return carriedAwayRepository.findById(id);
    }

    @Override
    public CarriedAway save(CarriedAway carriedAway) {
        return carriedAwayRepository.save(carriedAway);
    }

    @Override
    public void remove(Long id) {
      CarriedAway carriedAway = findById(id).get() ;
      carriedAway.setDeleted(true);
      carriedAwayRepository.save(carriedAway) ;
    }
}
