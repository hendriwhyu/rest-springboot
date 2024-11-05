package com.boot.controller;

import java.util.List; 
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.entity.Product;
import com.boot.response.ResponseHandler;
import com.boot.service.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/products")
    public ResponseEntity<Object> getAllProducts() {
        List<Product> products = productService.fetchAllProducts();
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, products);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.fetchProductById(id);
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, productOptional);
    }

    @PutMapping(path = "/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> updatedProductOptional = productService.updateProduct(id, product);
        return updatedProductOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean deletionStatus = productService.deleteProduct(id);
        if (deletionStatus) {
            return ResponseEntity.ok("Product with Id " + id + " has been deleted");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete product with Id " + id);
        }
    }

}
