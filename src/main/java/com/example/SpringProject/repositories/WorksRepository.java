package com.example.SpringProject.repositories;

import com.example.SpringProject.models.Employee;
import com.example.SpringProject.models.Part;
import com.example.SpringProject.models.Works;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WorksRepository extends JpaRepository<Works, Integer> {
    public List<Works> findByEmployee(Employee employee);

    public Optional<Works> findByEmployeeAndPartAndDayAndMonthAndYear(Employee employee, Part part, int day, int month, int year);
}
