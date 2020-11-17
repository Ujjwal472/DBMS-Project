package com.example.SpringProject.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("unused")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private int product_id;

    private String productName;
    private int total_available;
    private int total_defective;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Assembles> assembles;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ProductLog> productLog;

    @ManyToMany
    @JoinTable(
            name = "parts_used",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id")
    )
    private List<Part> parts_required;

    public List<ProductLog> getProductLog() {
        return productLog;
    }

    public void setProductLog(List<ProductLog> productLog) {
        this.productLog = productLog;
    }

    public List<Part> getParts_required() {
        return parts_required;
    }

    public void setParts_required(List<Part> parts_required) {
        this.parts_required = parts_required;
    }

    public List<Assembles> getAssembles() {
        return assembles;
    }

    public void setAssembles(List<Assembles> assembles) {
        this.assembles = assembles;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", productName='" + productName + '\'' +
                ", total_available=" + total_available +
                ", total_defective=" + total_defective +
                ", assembles=" + assembles +
                '}';
    }
}
