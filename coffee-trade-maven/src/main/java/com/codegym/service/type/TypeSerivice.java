package com.codegym.service.type;

import com.codegym.model.Type;
import com.codegym.repository.ITypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeSerivice implements ITypeService{

    @Autowired
    private ITypeRepository typeRepository ;

    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> findAllNotDeleted() {
        return typeRepository.findAllNotDeleted();
    }

    @Override
    public Optional<Type> findById(Long id) {
        return typeRepository.findById(id);
    }

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public void remove(Long id) {
        Type type = findById(id).get() ;
        type.setDeleted(true);
        save(type) ;
    }
}
