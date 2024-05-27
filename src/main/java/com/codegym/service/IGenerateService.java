package com.codegym.service;

import com.codegym.exception.DuplicateCodeException;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void remove(Long id);

    void save(T t) throws DuplicateCodeException;
}
