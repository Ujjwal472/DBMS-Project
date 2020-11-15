package com.example.SpringProject.repositories;

import com.example.SpringProject.models.Works;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorksRepository extends JpaRepository<Works, Integer> {
}
