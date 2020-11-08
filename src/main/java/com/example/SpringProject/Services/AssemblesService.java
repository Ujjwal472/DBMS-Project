package com.example.SpringProject.Services;

import com.example.SpringProject.models.Assembles;
import com.example.SpringProject.repositories.AssemblesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssemblesService {
    @Autowired
    AssemblesRepository assemblesRepository;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ProductService productService;

    public Assembles getAssemblesById(long assembles_id) {
        Optional<Assembles> optional = assemblesRepository.findById(assembles_id);
        Assembles assembles;
        if (optional.isPresent()) assembles = optional.get();
        else throw new RuntimeException(" No Assembles with id = " + assembles_id);
        return assembles;
    }

    public List<Assembles> getAssemblesByEmployeeId(int employee_id) {
        List<Assembles> all_assembles = assemblesRepository.getAssemblesByEmployee_id(employee_id);
        return all_assembles;
    }

    public List<Assembles> getAllAssembles() {
        List<Assembles> all_assembles = assemblesRepository.findAll();
        return all_assembles;
    }

    public void saveAssembles(Assembles assembles) {
        System.out.println("Starting to save");
        try {
            assemblesRepository.save(assembles);
            System.out.println("Saved");
        } catch (Exception e) {
            System.out.println("assembles object cannot be saved!");
        }
    }

    public void deleteByAssemblesId(long assembles_id) {
        assemblesRepository.deleteById(assembles_id);
    }

}
