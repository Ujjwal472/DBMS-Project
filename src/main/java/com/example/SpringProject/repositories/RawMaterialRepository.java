package com.example.SpringProject.repositories;

import com.example.SpringProject.models.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Integer> {
    public Optional<RawMaterial> findByTypeAndMaterialName(String type, String material_name);
}
