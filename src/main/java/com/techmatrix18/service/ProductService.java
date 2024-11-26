package com.techmatrix18.service;

import com.techmatrix18.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service class for {@link com.techmatrix18.model.Product}
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getProductsByCategoryId(Long id);

    Optional<Product> getProductById(Long id);

    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(Long id);

    Page<Product> getAllProductsSortedByTitle();
}
