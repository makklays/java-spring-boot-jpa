package com.techmatrix18.service.implementation;

import com.techmatrix18.model.Product;
import com.techmatrix18.repository.ProductRepository;
import com.techmatrix18.service.InterfaceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImpl implements InterfaceProduct {
    @Lazy
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        productRepository.findAll().forEach(list::add);

        return list;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public boolean addProduct(Product product) {
        Product pro = productRepository.save(product);
        if (!pro.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        Product pro = productRepository.save(product);
        if (!pro.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Long id) {
        Product product = productRepository.getById(id);
        if (!product.getTitle().isEmpty()) {
            productRepository.delete(product);
            return true;
        } else {
            return false;
        }
    }
}

