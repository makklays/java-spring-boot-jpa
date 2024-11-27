package com.techmatrix18.service.impl;

import com.techmatrix18.model.Category;
import com.techmatrix18.model.City;
import com.techmatrix18.repository.CategoryRepository;
import com.techmatrix18.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link CategoryService} interface.
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Service
public class CategoryServiceImpl implements CategoryService {
    @Lazy
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        categoryRepository.findAll().forEach(list::add);

        return list;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public boolean addCategory(Category category) {
        Category c = categoryRepository.save(category);
        if (!c.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateCategory(Category category) {
        Category c = categoryRepository.save(category);
        if (!c.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteCategory(Long id) {
        Category category = categoryRepository.getById(id);
        if (!category.getTitle().isEmpty()) {
            categoryRepository.delete(category);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Page<Category> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return categoryRepository.findAll(pageable);
    }
}

