package com.example.SpringProject.repositories;

import com.example.SpringProject.models.ProductLog;
import com.example.SpringProject.models.ProductionLogKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLogRepository extends JpaRepository<ProductLog, ProductionLogKey> {
}
