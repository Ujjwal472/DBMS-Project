package com.example.SpringProject.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class RawMaterial {
    @Id
    @GeneratedValue
    private int material_id;
    private String material_name;
    private int total_available;
    private String type;
    private double cost_per_unit;

    @OneToMany(mappedBy = "rawMaterial", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Requirement> required;

    public List<Requirement> getRequired() {
        return required;
    }

    public void setRequired(List<Requirement> required) {
        this.required = required;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public int getTotal_available() {
        return total_available;
    }

    public void setTotal_available(int total_available) {
        this.total_available = total_available;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost_per_unit() {
        return cost_per_unit;
    }

    public void setCost_per_unit(double cost_per_unit) {
        this.cost_per_unit = cost_per_unit;
    }
}
