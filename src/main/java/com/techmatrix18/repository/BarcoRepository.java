package com.techmatrix18.repository;

import com.techmatrix18.model.Barco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarcoRepository extends CrudRepository<Barco, Long> {
    List<Barco> findByID(Long id);
    List<Barco> findByWeightMore(Integer weight);
    List<Barco> findByYear(Integer year);

    Page<Barco> findAll(Pageable pageable);
}

