package com.erp.controller;

import com.erp.entity.Product;
import com.erp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    // Add Product - Only Admin
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = service.addProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    // Get All Products - Admin, Inventory Manager, Sales Executive
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INVENTORY_MANAGER', 'SALES_EXECUTIVE')")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    // Update Product - Only Admin
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = service.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    // Delete Product - Only Admin
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
