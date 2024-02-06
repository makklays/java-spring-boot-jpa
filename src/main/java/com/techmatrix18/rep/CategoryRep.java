package com.techmatrix18.rep;

import com.techmatrix18.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRep extends CrudRepository<Category, Long> {
    List<Category> findByID(Long id);
    List<Category> findByTitle(String firstname, String lastname);

    Page<Category> findAll(Pageable pageable);
}

