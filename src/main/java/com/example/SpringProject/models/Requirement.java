package com.example.SpringProject.models;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class Requirement {
    @Id
    @GeneratedValue
    private int requirement_id;
    private double units_required;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private RawMaterial rawMaterial;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;

    public int getRequirement_id() {
        return requirement_id;
    }

    public void setRequirement_id(int requirement_id) {
        this.requirement_id = requirement_id;
    }

    public double getUnits_required() {
        return units_required;
    }

    public void setUnits_required(double units_required) {
        this.units_required = units_required;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
