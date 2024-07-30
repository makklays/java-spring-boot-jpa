package com.techmatrix18.web.views;

import com.techmatrix18.model.Barco;
import com.techmatrix18.model.City;
import com.techmatrix18.model.Product;
import com.techmatrix18.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductViewsController {
    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    private final ProductService productService;
    private final StorehouseService storehouseService;
    private final CategoryService categoryService;

    public ProductViewsController(ProductService productService, StorehouseService storehouseService, CategoryService categoryService) {
        this.productService = productService;
        this.storehouseService = storehouseService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        //
        model.addAttribute("products", productService.getAllProducts());
        return "products/list";
    }

    @GetMapping("/add")
    public String add(Model model, Product product) {
        // cities
        //List<City> cities = cityService.getAllCities();
        model.addAttribute("product", product);

        model.addAttribute("categories", categoryService.getAllCategories());

        return "products/add";
    }

    @PostMapping("/add-post")
    public String addPost(Model model, @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "products/add";
        }

        productService.addProduct(product);

        return "redirect:/products/list";
    }

    @GetMapping("/edit/{productId}")
    public String edit(HttpServletRequest request, HttpServletResponse response, @PathVariable Long productId, Model model) throws Exception {
        Product product = productService.getProductById(productId);
        if (product.getId() != null) {
            model.addAttribute("product", product);
            logger.info("Product found..");
        } else {
            model.addAttribute("product", null);
            logger.warn("Error! Product not found..");
        }

        // cities
        //List<City> cities = cityService.getAllCities();
        //model.addAttribute("cities", cities);

        model.addAttribute("categories", categoryService.getAllCategories());

        return "products/edit";
    }

    @PostMapping("/update/{id}")
    public String editPost(@PathVariable("id") long id, @Valid Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            product.setId(id);
            return "products/edit";
        }

        productService.updateProduct(product);

        return "redirect:/products/list";
    }

    @GetMapping("/delete/{productId}")
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long productId) throws IOException {
        Product product = productService.getProductById(productId);
        if (product.getId() != null) {
            productService.deleteProduct(productId);
        }

        response.sendRedirect("/products/list");
    }

    @GetMapping("/{productId}")
    public String view(Model model, @PathVariable String productId) {
        Product product = productService.getProductById(Long.parseLong(productId));
        if (product.getId() != null) {
            model.addAttribute("product", product);
            logger.info("Product found..");
        } else {
            model.addAttribute("product", null);
            logger.info("Error! Product not found..");
        }

        return "products/view";
    }
}

