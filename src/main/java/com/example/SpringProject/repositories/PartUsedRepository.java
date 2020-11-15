package com.example.SpringProject.repositories;

import com.example.SpringProject.models.PartUsed;
import com.example.SpringProject.models.PartUsedKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartUsedRepository extends JpaRepository<PartUsed, PartUsedKey> {
}
