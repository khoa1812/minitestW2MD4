package com.codegym.service.impl;

import com.codegym.exception.DuplicateCodeException;
import com.codegym.model.Type;
import com.codegym.repository.ITypeRepository;
import com.codegym.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class TypeService implements ITypeService {
    @Autowired
    private ITypeRepository iTypeRepository;
    @Override
    public Iterable<Type> findAll() {
        return iTypeRepository.findAll();
    }

    @Override
    public void save(Type type) throws DuplicateCodeException {
        try {
            iTypeRepository.save(type);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateCodeException();
        }
    }

    @Override
    public Optional<Type> findById(Long id) {
        return iTypeRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iTypeRepository.deleteById(id);
    }
}
