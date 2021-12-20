package com.codegym.service.desk;

import com.codegym.model.Desk;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IDeskService extends IGeneralService<Desk> {

    List<Desk> findAllByDeletedIsFalseAndEmptyIsFalse() ;

    List<Desk> findAllNotDeletedAndEmpty() ;

    List<Desk> findAllNotDeletedAndNotEmpty() ;

}
