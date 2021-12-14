package com.codegym.repository;

import com.codegym.model.Staff;
import com.codegym.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITypeRepository extends JpaRepository<Type, Long> {

    @Query(value = "select t from Type t where t.isDeleted = false")
    List<Type> findAllNotDeleted() ;
}
