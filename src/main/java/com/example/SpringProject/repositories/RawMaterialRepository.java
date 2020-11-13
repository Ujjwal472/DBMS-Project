package com.example.SpringProject.repositories;

import com.example.SpringProject.models.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Integer> {
}
