package com.erp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
