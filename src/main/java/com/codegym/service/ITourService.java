package com.codegym.service;


import com.codegym.model.Tour;
import com.codegym.model.Type;

public interface ITourService extends IGenerateService<Tour> {
    Iterable<Tour> findAllByType(Type type);
}

