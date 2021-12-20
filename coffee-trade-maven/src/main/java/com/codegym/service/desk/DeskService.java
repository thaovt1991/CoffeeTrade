package com.codegym.service.desk;

import com.codegym.model.Desk;
import com.codegym.repository.IDeskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DeskService implements  IDeskService{

    @Autowired
    private IDeskRepository deskRepository ;

    @Override
    public List<Desk> findAll() {
        return deskRepository.findAll();
    }

    @Override
    public List<Desk> findAllNotDeleted() {
        return deskRepository.findAllNotDeleted();
    }

    @Override
    public Optional<Desk> findById(Long id) {
        return deskRepository.findById(id);
    }

    @Override
    public Desk save(Desk desk) {
        return deskRepository.save(desk);
    }

    @Override
    public void remove(Long id) {
      Desk desk = findById(id).get() ;
      desk.setDeleted(true);
      deskRepository.save(desk) ;
    }

    @Override
    public List<Desk> findAllByDeletedIsFalseAndEmptyIsFalse() {
        return deskRepository.findAllByDeletedIsFalseAndEmptyIsFalse();
    }

    @Override
    public List<Desk> findAllNotDeletedAndNotEmpty() {
        return deskRepository.findAllNotDeletedAndNotEmpty();
    }

    @Override
    public List<Desk> findAllNotDeletedAndEmpty() {
        return deskRepository.findAllNotDeletedAndEmpty();
    }
}
