package com.techmatrix18.repository;

import com.techmatrix18.model.Storehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorehouseRepository extends CrudRepository<Storehouse, Long> {
    Storehouse getById(Long id);
    List<Storehouse> findByTitle(String title);
    List<Storehouse> findByCityId(Integer cityId);

    Page<Storehouse> findAll(Pageable pageable);
}

