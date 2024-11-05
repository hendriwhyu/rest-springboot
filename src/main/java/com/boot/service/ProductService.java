package com.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.boot.entity.Product;
import com.boot.repo.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("Failed to save product: " + e.getMessage());
        }
    }

    public List<Product> fetchAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("Failed to fetch all products: " + e.getMessage());
        }
    }

    public Optional<Product> fetchProductById(Long id) {
        try {
            return productRepository.findById(id);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("Failed to fetch product by Id: " + e.getMessage());
        }
    }

    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        try {
            Optional<Product> existingProductOptional = productRepository.findById(id);

            if (existingProductOptional.isPresent()) {
                Product existingProduct = existingProductOptional.get();
                existingProduct.setName(updatedProduct.getName());
                existingProduct.setPrice(updatedProduct.getPrice());
                existingProduct.setQuantity(updatedProduct.getQuantity());
                Product savedEntity = productRepository.save(existingProduct);
                return Optional.of(savedEntity);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("Failed to update product: " + e.getMessage());
        }
    }

    public boolean deleteProduct(Long id){
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("Failed to delete product: " + e.getMessage());
        }
    }
}
