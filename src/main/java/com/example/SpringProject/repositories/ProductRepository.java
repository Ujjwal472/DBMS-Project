package com.example.SpringProject.repositories;

import com.example.SpringProject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Optional<Product> findByProductName(String product_name);
}
