package com.example.SpringProject.repositories;

import com.example.SpringProject.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
