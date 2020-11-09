package com.example.SpringProject.repositories;

import com.example.SpringProject.models.Customer;
import com.example.SpringProject.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    public List<Purchase> findByCustomer(Customer customer);
}
