package com.techmatrix18.service;

import com.techmatrix18.model.Category;
import java.util.List;

/**
 * Service class for {@link com.techmatrix18.model.Category}
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);

    boolean addCategory(Category category);
    boolean updateCategory(Category category);
    boolean deleteCategory(Long id);
}
