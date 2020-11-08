package com.example.SpringProject.repositories;

import com.example.SpringProject.models.Assembles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssemblesRepository extends JpaRepository<Assembles, Long> {
    @Query(value = "select * from assembles a where a.employee_id=?1", nativeQuery = true)
    public List<Assembles> getAssemblesByEmployee_id(int id);
}
