package com.techmatrix18.repository;

import com.techmatrix18.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getById(Long id);
    List<Category> findByTitle(String title);

    Page<Category> findAll(Pageable pageable);
}

