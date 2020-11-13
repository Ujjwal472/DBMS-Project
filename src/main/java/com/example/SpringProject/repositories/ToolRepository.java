package com.example.SpringProject.repositories;

import com.example.SpringProject.models.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolRepository extends JpaRepository<Tool, Integer> {
}
