package com.example.SpringProject.Services;

import com.example.SpringProject.models.Product;
import com.example.SpringProject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Product getProductById(int product_id) {
        Optional<Product> optional = productRepository.findById(product_id);
        Product product;
        if (optional.isPresent()) product = optional.get();
        else throw new RuntimeException("No product with id = " + product_id);
        return product;
    }

    public List<Product> getAllProduct() {
        List<Product> all_product = productRepository.findAll();
        return all_product;
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProductById(int product_id) {
        productRepository.deleteById(product_id);
    }

    public boolean checkByProductName(String product_name) {
        Optional<Product> optional = productRepository.findByProductName(product_name);
        return optional.isPresent();
    }

}
