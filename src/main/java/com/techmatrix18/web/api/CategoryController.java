package com.techmatrix18.web.api;

import com.techmatrix18.model.Category;
import com.techmatrix18.model.City;
import com.techmatrix18.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Simple controller for Category
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path = "/{id}")
    public Category getCategories(@PathVariable String id) throws ValidationException {
        Long catId = Long.parseLong(id);

        return categoryService.getCategoryById(catId);
    }

    @GetMapping(path = "/all")
    public List<Category> getCategories() throws ValidationException {
        return categoryService.getAllCategories();
    }

    @GetMapping(path = "/page", produces = "application/json;charset=UTF-8")
    public Object getPage(@RequestParam String pageNo, @RequestParam String pageSize) {
        int pNo = Integer.parseInt(pageNo);
        int pSize = Integer.parseInt(pageSize);

        Page<Category> categories = categoryService.findPaginated(pNo, pSize);
        if (categories != null) {
            return categories;
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find categories with pageNo=" + pageNo + " and pageSize=" + pageSize + " \"}";
        }
    }

    @PostMapping(path = "/add")
    @Transactional(rollbackFor = Exception.class)
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

