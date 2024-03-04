package com.techmatrix18.service;

import com.techmatrix18.model.Category;
import java.util.List;

public interface InterfaceCategory {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);

    boolean addCategory(Category category);
    boolean updateCategory(Category category);
    boolean deleteCategory(Long id);
}
