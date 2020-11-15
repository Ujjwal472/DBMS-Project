package com.example.SpringProject.repositories;

import com.example.SpringProject.models.Assembles;
import com.example.SpringProject.models.Employee;
import com.example.SpringProject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AssemblesRepository extends JpaRepository<Assembles, Long> {
    @Query(value = "select * from assembles a where a.employee_id=?1", nativeQuery = true)
    public List<Assembles> getAssemblesByEmployee_id(int id);
    public Optional<Assembles> findByEmployeeAndProductAndDayAndMonthAndYear(Employee employee, Product product, int day, int month, int year);
}
