package com.codegym.service.staff;

import com.codegym.model.Staff;
import com.codegym.repository.IStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService implements IStaffService{

    @Autowired
    private IStaffRepository staffRepository ;

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public List<Staff> findAllNotDeleted() {
        return staffRepository.findAllNotDeleted();
    }

    @Override
    public Optional<Staff> findById(Long id) {
        return staffRepository.findById(id);
    }

    @Override
    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public void remove(Long id) {
           Staff staff = findById(id).get() ;
           staff.setDeleted(true);
           save(staff) ;
    }
}
