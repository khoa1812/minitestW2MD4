package com.codegym.repository;


import com.codegym.model.Tour;
import com.codegym.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ITourRepository extends CrudRepository<Tour, Long> {
    Iterable<Tour> findAllByType(Type type);

    Page<Tour> findAll(Pageable pageable);

    Page<Tour> findAllByCodeContaining(Pageable pageable, String name);
}