package com.codegym.repository;

import com.codegym.model.Order;
import com.codegym.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStaffRepository extends JpaRepository<Staff, Long> {

    @Query(value = "select s from Staff s where s.isDeleted = false")
    List<Staff> findAllNotDeleted() ;
}
