package com.example.SpringProject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private int product_id;

    private String product_name;
    private int total_available;
    private int total_defective;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Assembles> assembles;

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

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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
                ", product_name='" + product_name + '\'' +
                ", total_available=" + total_available +
                ", total_defective=" + total_defective +
                ", assembles=" + assembles +
                '}';
    }
}
