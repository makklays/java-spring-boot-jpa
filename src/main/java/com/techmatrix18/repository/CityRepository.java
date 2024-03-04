package com.techmatrix18.repository;

import com.techmatrix18.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City getById(Long id);
    List<City> findByTitle(String title);

    Page<City> findAll(Pageable pageable);
}

