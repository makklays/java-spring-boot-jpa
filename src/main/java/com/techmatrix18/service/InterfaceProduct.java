package com.techmatrix18.service;

import com.techmatrix18.model.Product;
import java.util.List;

/**
 * Service class for {@link com.techmatrix18.model.Product}
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

public interface InterfaceProduct {
    List<Product> getAllProducts();
    Product getProductById(Long id);

    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(Long id);
}
