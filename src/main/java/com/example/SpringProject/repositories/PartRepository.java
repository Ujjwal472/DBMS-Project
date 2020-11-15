package com.example.SpringProject.repositories;

import com.example.SpringProject.models.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Integer> {
    public Optional<Part> findByPartName(String part_name);
}
