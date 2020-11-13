package com.example.SpringProject.repositories;

import com.example.SpringProject.models.Part;
import com.example.SpringProject.models.RawMaterial;
import com.example.SpringProject.models.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequirementRepository extends JpaRepository<Requirement, Integer> {
    public List<Requirement> findByPart(Part part);
    public List<Requirement> findByRawMaterial(RawMaterial material);
    public Optional<Requirement> findByRawMaterialAndPart(RawMaterial material, Part part);
}
