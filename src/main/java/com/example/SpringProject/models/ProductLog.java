package com.example.SpringProject.models;

import javax.persistence.*;

@Entity
public class ProductLog {
    @EmbeddedId
    ProductionLogKey productionKey;

    private int total_produced;
    private int total_defective;

    private double total_cost;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductionLogKey getProductionKey() {
        return productionKey;
    }

    public void setProductionKey(ProductionLogKey productionKey) {
        this.productionKey = productionKey;
    }

    public int getTotal_defective() {
        return total_defective;
    }

    public void setTotal_defective(int total_defective) {
        this.total_defective = total_defective;
    }

    public int getTotal_produced() {
        return total_produced;
    }

    public void setTotal_produced(int total_produced) {
        this.total_produced = total_produced;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }
}
