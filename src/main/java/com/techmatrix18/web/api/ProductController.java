package com.techmatrix18.web.api;

import com.techmatrix18.model.Category;
import com.techmatrix18.model.Product;
import com.techmatrix18.service.CategoryService;
import com.techmatrix18.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

/**
 * Simple controller for Product
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
    }

    @GetMapping(path = "/page-sort")
    public Page<Product> getSort() throws ValidationException {
        return productService.getAllProductsSortedByTitle();
    }

    @GetMapping(path = "/all")
    public List<Product> getProducts() throws ValidationException {
        return productService.getAllProducts();
    }

    @GetMapping(path = "/{id}")
    public Object getOneProduct(@PathVariable String id) throws ValidationException {
        Long productId = Long.parseLong(id);
        Optional<Product> product = productService.getProductById(productId);
        if (product.isPresent()) {
            return product.get();
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find product with ID=" + id + "\"}";
        }
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addProduct (@RequestParam String title, @RequestParam String description, @RequestParam Integer weight, @RequestParam boolean isDangerous, @RequestParam boolean isGlass, @RequestParam String categoryId) {
        Product p = new Product();
        p.setTitle(title);
        p.setDescription(description);
        p.setWeight(weight);
        p.setIsDangerous(isDangerous);
        p.setIsGlass(isGlass);
        Category category = categoryService.getCategoryById(Long.parseLong(categoryId));
        p.setCategory(category);
        if (productService.addProduct(p)) {
            return "{\"status\": \"success\", \"message\": \"Product added successfully!\"}";
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't add product\"}";
        }
    }

    @PatchMapping(path = "/update")
    public @ResponseBody String updateProduct (@RequestParam Long productId, @RequestParam String title, @RequestParam String description, @RequestParam Integer weight, @RequestParam boolean isDangerous, @RequestParam boolean isGlass, @RequestParam String categoryId) {
        Optional<Product> p = productService.getProductById(productId);
        if (p.isPresent()) {
            p.get().setTitle(title);
            p.get().setDescription(description);
            p.get().setWeight(weight);
            p.get().setIsDangerous(isDangerous);
            p.get().setIsGlass(isGlass);
            Category category = categoryService.getCategoryById(Long.parseLong(categoryId));
            p.get().setCategory(category);
            if (productService.updateProduct(p.get())) {
                return "{\"status\": \"success\", \"message\": \"Product updated successfully!\"}";
            } else {
                return "{\"status\": \"error\", \"message\": \"Didn't update product\"}";
            }
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find product with ID=" + productId + "\"}";
        }
    }

    @DeleteMapping(path = "/delete/{productId}")
    public @ResponseBody String deleteProduct (@PathVariable Long productId) {
        Optional<Product> p = productService.getProductById(productId);
        if (p.isPresent()) {
            if (productService.deleteProduct(productId)) {
                return "{\"status\": \"success\", \"message\": \"Product deleted successfully!\"}";
            } else {
                return "{\"status\": \"error\", \"message\": \"Didn't delete product\"}";
            }
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find product with ID=" + productId + "\"}";
        }
    }
}

