package com.codegym.service.impl;

import com.codegym.exception.DuplicateCodeException;
import com.codegym.model.Tour;
import com.codegym.model.Type;
import com.codegym.repository.ITourRepository;
import com.codegym.service.ITourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TourService implements ITourService {
    @Autowired
    private ITourRepository iTourRepository;

    @Override
    public Iterable<Tour> findAll() {
        return iTourRepository.findAll();
    }

    @Override
    public void save(Tour tour) throws DuplicateCodeException {
        try {
            iTourRepository.save(tour);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateCodeException();
        }
    }


    @Override
    public Optional<Tour> findById(Long id) {
        return iTourRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iTourRepository.deleteById(id);
    }

    @Override
    public Iterable<Tour> findAllByType(Type type) {
        return iTourRepository.findAllByType(type);
    }

    @Override
    public Page<Tour> findAll(Pageable pageable) {
        return iTourRepository.findAll(pageable);
    }

    @Override
    public Page<Tour> findAllByCodeContaining(Pageable pageable, String code) throws DuplicateCodeException {
        Page<Tour> tours = iTourRepository.findAllByCodeContaining(pageable, code);
        if (tours.isEmpty()) {
            throw new DuplicateCodeException();
        }
        return tours;
    }


}

