package com.codegym.service.bill;

import com.codegym.model.Bill;
import com.codegym.repository.IBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService implements IBillService{

    @Autowired
    private IBillRepository billRepository ;

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public List<Bill> findAllNotDeleted() {
        return billRepository.findAllNotDeleted();
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return billRepository.findById(id);
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void remove(Long id) {
        Bill bill = findById(id).get() ;
        bill.setDeleted(true);
        billRepository.save(bill) ;
    }
}
