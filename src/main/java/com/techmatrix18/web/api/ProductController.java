package com.techmatrix18.web.api;

import com.techmatrix18.model.Product;
import com.techmatrix18.repository.ProductRepository;
import com.techmatrix18.service.ProductService;
import com.techmatrix18.service.implementation.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

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

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
    }

    @GetMapping(path = "/all")
    public List<Product> getProducts() throws ValidationException {
        return productService.getAllProducts();
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addProduct (@RequestParam String title, @RequestParam String description, @RequestParam Integer weight, @RequestParam Integer isDangerous, @RequestParam Integer isGlass, @RequestParam Long categoryId) {
        Product p = new Product();
        p.setTitle(title);
        p.setDescription(description);
        p.setWeight(weight);
        p.setIsDangerous(isDangerous);
        p.setIsGlass(isGlass);
        p.setCategoryId(categoryId);
        productService.addProduct(p);
        return "Saved";
    }

    @PatchMapping(path = "/update")
    public @ResponseBody String updateProduct (@RequestParam Long productId, @RequestParam String title, @RequestParam String description, @RequestParam Integer weight, @RequestParam Integer isDangerous, @RequestParam Integer isGlass, @RequestParam Long categoryId) {
        Product p = productService.getProductById(productId);
        if (p.getId() != null) {
            p.setTitle(title);
            p.setDescription(description);
            p.setWeight(weight);
            p.setIsDangerous(isDangerous);
            p.setIsGlass(isGlass);
            p.setCategoryId(categoryId);
            productService.updateProduct(p);
        }
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{productId:\\\\d+}")
    public @ResponseBody String deleteProduct (@PathVariable Long productId) {
        Product p = productService.getProductById(productId);
        if (p.getId() != null) {
            productService.deleteProduct(productId);
        }
        return "Deleted";
    }
}

