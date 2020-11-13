package com.example.SpringProject.Services;

import com.example.SpringProject.models.Part;
import com.example.SpringProject.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartService {
    @Autowired
    PartRepository partRepository;

    public List<Part> getAllPart() {
        return partRepository.findAll();
    }
    public Part getPartById(int part_id) {
        Optional<Part> optional = partRepository.findById(part_id);
        if (optional.isPresent()) return optional.get();
        else throw new RuntimeException("No part exists with id = " + part_id);
    }
    public void savePart(Part part) {
        partRepository.save(part);
    }
    public void deletePartById(int part_id) {
        partRepository.deleteById(part_id);
    }
}
