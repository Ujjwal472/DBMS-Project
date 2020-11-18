package com.example.SpringProject.Services;

import com.example.SpringProject.models.PartLog;
import com.example.SpringProject.models.PartLogKey;
import com.example.SpringProject.repositories.PartLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void deleteById(PartLogKey pk) {
        partLogRepository.deleteById(pk);
    }

    public boolean checkByPartLogId(PartLogKey pk) {
        Optional<PartLog> optional = partLogRepository.findById(pk);
        return optional.isPresent();
    }
}
