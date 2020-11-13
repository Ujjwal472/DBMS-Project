package com.example.SpringProject.Services;

import com.example.SpringProject.models.Part;
import com.example.SpringProject.models.RawMaterial;
import com.example.SpringProject.models.Requirement;
import com.example.SpringProject.repositories.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequirementService {
    @Autowired
    RequirementRepository requirementRepository;

    @Autowired
    MaterialService materialService;

    @Autowired
    PartService partService;

    public List<Requirement> getRequirementsByPartId(int part_id) { return requirementRepository.findByPart(partService.getPartById(part_id)); }
    public List<Requirement> getRequirementByRawMaterial(RawMaterial material) {
        List<Requirement> all_requirements = requirementRepository.findByRawMaterial(material);
        return all_requirements;
    }
    public Requirement getRequirementById(int requirement_id) {
        Optional<Requirement> optional = requirementRepository.findById(requirement_id);
        if (optional.isPresent()) return optional.get();
        else throw new RuntimeException("No requirement with id = " + requirement_id);
    }
    public void deleteRequirementById(int requirement_id) {
        requirementRepository.deleteById(requirement_id);
    }
    public void saveRequirement(Requirement requirement) { requirementRepository.save(requirement); }
    public Requirement getByPartAndMaterial(Part part, RawMaterial material) {
        Optional<Requirement> optional = requirementRepository.findByRawMaterialAndPart(material, part);
        if (optional.isPresent()) return optional.get();
        else throw new RuntimeException("No such requirement");
    }
}
