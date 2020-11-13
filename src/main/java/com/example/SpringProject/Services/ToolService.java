package com.example.SpringProject.Services;

import com.example.SpringProject.models.Tool;
import com.example.SpringProject.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToolService {
    @Autowired
    ToolRepository toolRepository;

    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }
    public void saveTool(Tool tool) { toolRepository.save(tool); }
    public Tool getToolById(int tool_id) {
        Optional<Tool> optional = toolRepository.findById(tool_id);
        if (optional.isPresent()) return optional.get();
        else throw new RuntimeException("No tool with id = " + tool_id);
    }
    public void deleteToolById(int tool_id) { toolRepository.deleteById(tool_id); }
}
