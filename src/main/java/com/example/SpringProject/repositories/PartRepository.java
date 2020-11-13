package com.example.SpringProject.repositories;

import com.example.SpringProject.models.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Integer> {
}
