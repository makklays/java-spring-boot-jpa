package com.techmatrix18.web.views;

import com.techmatrix18.model.Barco;
import com.techmatrix18.model.Category;
import com.techmatrix18.model.Product;
import com.techmatrix18.service.CategoryService;
import com.techmatrix18.service.ProductService;
import com.techmatrix18.service.StorehouseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/categories")
public class CategoryViewsController {

    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    private final CategoryService categoryService;

    public CategoryViewsController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        //
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories/list";
    }

    @GetMapping("/add")
    public String add(Model model, Category category) {
        // cities
        //List<City> cities = cityService.getAllCities();
        model.addAttribute("category", category);

        return "categories/add";
    }

    @PostMapping("/add-post")
    public String addPost(Model model, @Valid Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "categories/add";
        }

        categoryService.addCategory(category);

        return "redirect:/categories/list";
    }

    @GetMapping("/edit/{categoryId}")
    public String edit(HttpServletRequest request, HttpServletResponse response, @PathVariable Long categoryId, Model model) throws Exception {
        Category category = categoryService.getCategoryById(categoryId);
        if (category.getId() != null) {
            model.addAttribute("category", category);
            logger.info("Category found..");
        } else {
            model.addAttribute("category", null);
            logger.warn("Error! Category not found..");
        }

        // cities
        //List<City> cities = cityService.getAllCities();
        //model.addAttribute("cities", cities);

        return "categories/edit";
    }

    @PostMapping("/update/{id}")
    public String editPost(@PathVariable("id") long id, @Valid Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            category.setId(id);
            return "categories/edit";
        }

        categoryService.updateCategory(category);

        return "redirect:/categories/list";
    }

    @GetMapping("/delete/{categoryId}")
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long categoryId) throws IOException {
        Category category = categoryService.getCategoryById(categoryId);
        if (category.getId() != null) {
            categoryService.deleteCategory(categoryId);
        }

        response.sendRedirect("/categories/list");
    }

    @GetMapping("/{categoryId}")
    public String view(Model model, @PathVariable String categoryId) {
        Category category = categoryService.getCategoryById(Long.parseLong(categoryId));
        if (category.getId() != null) {
            model.addAttribute("category", category);
            logger.info("Category found..");
        } else {
            model.addAttribute("category", null);
            logger.info("Error! Category not found..");
        }

        return "categories/view";
    }
}

