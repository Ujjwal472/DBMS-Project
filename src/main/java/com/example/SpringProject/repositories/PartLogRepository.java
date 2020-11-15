package com.example.SpringProject.repositories;

import com.example.SpringProject.models.PartLog;
import com.example.SpringProject.models.PartLogKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartLogRepository extends JpaRepository<PartLog, PartLogKey> {
    public List<PartLog> findByPartLogKeyPartId(int part_id);
}
