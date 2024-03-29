package com.example.SpringProject.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Tool {
    @Id
    @GeneratedValue
    private int tool_id;
    private String toolName;
    private int total_available;
    private int total_defective;

    @ManyToMany(mappedBy = "tools_used")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Part> used_in;

    public int getTool_id() {
        return tool_id;
    }

    public void setTool_id(int tool_id) {
        this.tool_id = tool_id;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public int getTotal_available() {
        return total_available;
    }

    public void setTotal_available(int total_available) {
        this.total_available = total_available;
    }

    public int getTotal_defective() {
        return total_defective;
    }

    public void setTotal_defective(int total_defective) {
        this.total_defective = total_defective;
    }

    public List<Part> getUsed_in() {
        return used_in;
    }

    public void setUsed_in(List<Part> used_in) {
        this.used_in = used_in;
    }
}
