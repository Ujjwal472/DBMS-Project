package com.example.SpringProject.models;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class PartLog {
    @EmbeddedId
    PartLogKey partLogKey;
    private int total_produced;
    private int total_defective;
    private double total_cost;

    @ManyToOne
    @MapsId("partId")
    @JoinColumn(name = "part_id")
    private Part log_part;

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public Part getLog_part() {
        return log_part;
    }

    public void setLog_part(Part log_part) {
        this.log_part = log_part;
    }

    public PartLogKey getPartLogKey() {
        return partLogKey;
    }

    public void setPartLogKey(PartLogKey partLogKey) {
        this.partLogKey = partLogKey;
    }

    public int getTotal_produced() {
        return total_produced;
    }

    public void setTotal_produced(int total_produced) {
        this.total_produced = total_produced;
    }

    public int getTotal_defective() {
        return total_defective;
    }

    public void setTotal_defective(int total_defective) {
        this.total_defective = total_defective;
    }
}
