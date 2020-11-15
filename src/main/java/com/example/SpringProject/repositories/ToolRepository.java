package com.example.SpringProject.repositories;

import com.example.SpringProject.models.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToolRepository extends JpaRepository<Tool, Integer> {
    public Optional<Tool> findByToolName(String tool_name);
}
