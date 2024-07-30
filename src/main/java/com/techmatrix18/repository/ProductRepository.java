package com.techmatrix18.repository;

import com.techmatrix18.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product getById(Long id);
    List<Product> findByTitle(String title);
    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByWeight(Integer distance);
    //List<Product> findByWeightLess(Integer distance);
    //List<Product> findByWeightMore(Integer distance);

    Page<Product> findAll(Pageable pageable);
}

