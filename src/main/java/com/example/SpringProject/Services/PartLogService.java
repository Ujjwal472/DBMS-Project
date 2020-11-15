package com.example.SpringProject.Services;

import com.example.SpringProject.models.PartLog;
import com.example.SpringProject.repositories.PartLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartLogService {
    @Autowired
    PartLogRepository partLogRepository;

    public List<PartLog> getAllPartLog() {
        return partLogRepository.findAll();
    }

    public List<PartLog> getPartLogsByPartId(int part_id) {
        return partLogRepository.findByPartLogKeyPartId(part_id);
    }

    public void savePartLog(PartLog partLog) {
        partLogRepository.save(partLog);
    }
}
