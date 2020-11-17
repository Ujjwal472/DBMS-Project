package com.example.SpringProject.repositories;

import com.example.SpringProject.models.Employee;
import com.example.SpringProject.models.Works;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorksRepository extends JpaRepository<Works, Integer> {
    public List<Works> findByEmployee(Employee employee);
}
