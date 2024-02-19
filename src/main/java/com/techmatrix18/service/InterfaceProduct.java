package com.techmatrix18.service;

import com.techmatrix18.model.Product;
import java.util.List;

public interface InterfaceProduct {
    List<Product> getAllProducts();
    Product getProductById(Long id);

    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(Long id);
}
