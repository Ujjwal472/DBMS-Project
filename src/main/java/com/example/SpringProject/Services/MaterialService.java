package com.example.SpringProject.Services;

import com.example.SpringProject.models.RawMaterial;
import com.example.SpringProject.repositories.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    @Autowired
    RawMaterialRepository materialRepository;

    public List<RawMaterial> getAllMaterials() { return materialRepository.findAll(); }
    public void saveMaterial(RawMaterial material) { materialRepository.save(material); }
    public RawMaterial getMaterialById(int material_id) {
        Optional<RawMaterial> optional = materialRepository.findById(material_id);
        if (optional.isPresent()) return optional.get();
        else throw new RuntimeException("No material with id = " + material_id);
    }
    public void deleteMaterialById(int material_id) {
        materialRepository.deleteById(material_id);
    }
}
