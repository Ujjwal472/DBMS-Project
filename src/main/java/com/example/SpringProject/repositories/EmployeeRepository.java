package com.example.SpringProject.repositories;

import com.example.SpringProject.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Optional<Employee> findByAadhaarNumber(long aadhaar_number);
}
