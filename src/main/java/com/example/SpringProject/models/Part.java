package com.example.SpringProject.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class Part {
    @Id
    @GeneratedValue
    private int part_id;
    private String partName;
    private int total_available;
    private int total_defective;
    private double total_material_cost;

    @OneToMany(mappedBy = "part", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Requirement> requirements;

    @ManyToMany
    @JoinTable(
            name = "tools_used",
            joinColumns = @JoinColumn(name = "part_id"),
            inverseJoinColumns = @JoinColumn(name = "tool_id")
    )
    private List<Tool> tools_used;

    @OneToMany(mappedBy = "log_part", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PartLog> partLogs;

    @OneToMany(mappedBy = "part", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Works> workedOn;

    public List<PartLog> getPartLogs() {
        return partLogs;
    }

    public void setPartLogs(List<PartLog> partLogs) {
        this.partLogs = partLogs;
    }

    public List<Tool> getTools_used() {
        return tools_used;
    }

    public void setTools_used(List<Tool> tools_used) {
        this.tools_used = tools_used;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    public int getPart_id() {
        return part_id;
    }

    public void setPart_id(int part_id) {
        this.part_id = part_id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
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

    public double getTotal_material_cost() {
        return total_material_cost;
    }

    public void setTotal_material_cost(double total_material_cost) {
        this.total_material_cost = total_material_cost;
    }
}
