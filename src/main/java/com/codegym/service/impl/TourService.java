package com.codegym.service.impl;

import com.codegym.model.Tour;
import com.codegym.model.Type;
import com.codegym.repository.ITourRepository;
import com.codegym.service.ITourService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void save(Tour tour) {
        iTourRepository.save(tour);
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
}

