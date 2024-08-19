package com.techmatrix18.repository;

import com.techmatrix18.model.Barco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarcoRepository extends JpaRepository<Barco, Long> {
    Barco getById(Long id);
    List<Barco> findByWeight(Integer weight);
    List<Barco> findByYear(Integer year);
    Page<Barco> findAll(Pageable pageable);
}

