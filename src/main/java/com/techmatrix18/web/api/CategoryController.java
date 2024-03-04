package com.techmatrix18.web.api;

import com.techmatrix18.model.Category;
import com.techmatrix18.repository.CategoryRepository;
import com.techmatrix18.service.implementation.CategoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

/************************************
 * Author: Alexander Kuziv
 *  Email: makklays@gmail.com
 *   Date: 19-02-2024
 ***********************************/

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryImpl categoryService;

    @GetMapping(path = "/all")
    public List<Category> getCategories() throws ValidationException {
        return categoryService.getAllCategories();
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addCategory (@RequestParam String title, @RequestParam String description) {
        Category c = new Category();
        c.setTitle(title);
        c.setDescription(description);
        categoryService.addCategory(c);
        return "Saved";
    }

    @PatchMapping(path = "/update")
    public @ResponseBody String updateCategory (@RequestParam Long categoryId, @RequestParam String title, @RequestParam String description) {
        Category c = categoryService.getCategoryById(categoryId);
        if (c.getId() != null) {
            c.setTitle(title);
            c.setDescription(description);
            categoryService.updateCategory(c);
        }
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{categoryId:\\\\d+}")
    public @ResponseBody String deleteCategory (@PathVariable Long categoryId) {
        Category c = categoryService.getCategoryById(categoryId);
        if (c.getId() != null) {
            categoryService.deleteCategory(categoryId);
        }
        return "Deleted";
    }
}

