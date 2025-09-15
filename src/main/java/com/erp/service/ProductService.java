package com.erp.service;

import com.erp.entity.Product;
import com.erp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        // Ensure no id is sent for creation
        product.setId(null); 
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setSku(updatedProduct.getSku());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setUnitPrice(updatedProduct.getUnitPrice());
            existingProduct.setCurrentStock(updatedProduct.getCurrentStock());
            existingProduct.setReorderLevel(updatedProduct.getReorderLevel());
            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
