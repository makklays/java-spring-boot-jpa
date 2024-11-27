package com.techmatrix18.service.impl;

import com.techmatrix18.model.Product;
import com.techmatrix18.model.Storehouse;
import com.techmatrix18.repository.ProductRepository;
import com.techmatrix18.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link ProductService} interface.
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Service
public class ProductServiceImpl implements ProductService {

    Pageable firstPageWithTwoElements = PageRequest.of(0, 2, Sort.by("title").ascending());

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
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        return productRepository.findByCategoryId(id);
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

    @Override
    public Page<Product> getAllProductsSortedByTitle() {
        Page<Product> allProductsSortedByTitle = productRepository.findAll(this.firstPageWithTwoElements);

        return allProductsSortedByTitle;
    }

    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return productRepository.findAll(pageable);
    }
}

