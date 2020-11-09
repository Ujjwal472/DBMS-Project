package com.example.SpringProject.models;

import javax.persistence.*;

@Entity
public class Purchase {
    @Id
    @GeneratedValue
    private long purchase_id;
    private int purchase_day;
    private int purchase_month;
    private int purchase_year;
    private int transportation_cost;
    private int selling_price;
    private int amount;
    private String transaction_status;
    private String delivery_status;
    private  int delivery_day;
    private int delivery_month;
    private int delivery_year;
    private int guarantee_given;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public long getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(long purchase_id) {
        this.purchase_id = purchase_id;
    }

    public int getPurchase_day() {
        return purchase_day;
    }

    public void setPurchase_day(int purchase_day) {
        this.purchase_day = purchase_day;
    }

    public int getPurchase_month() {
        return purchase_month;
    }

    public void setPurchase_month(int purchase_month) {
        this.purchase_month = purchase_month;
    }

    public int getPurchase_year() {
        return purchase_year;
    }

    public void setPurchase_year(int purchase_year) {
        this.purchase_year = purchase_year;
    }

    public int getTransportation_cost() {
        return transportation_cost;
    }

    public void setTransportation_cost(int transportation_cost) {
        this.transportation_cost = transportation_cost;
    }

    public int getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTransaction_status() {
        return transaction_status;
    }

    public void setTransaction_status(String transaction_status) {
        this.transaction_status = transaction_status;
    }

    public String getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(String delivery_status) {
        this.delivery_status = delivery_status;
    }

    public int getDelivery_day() {
        return delivery_day;
    }

    public void setDelivery_day(int delivery_day) {
        this.delivery_day = delivery_day;
    }

    public int getDelivery_month() {
        return delivery_month;
    }

    public void setDelivery_month(int delivery_month) {
        this.delivery_month = delivery_month;
    }

    public int getDelivery_year() {
        return delivery_year;
    }

    public void setDelivery_year(int delivery_year) {
        this.delivery_year = delivery_year;
    }

    public int getGuarantee_given() {
        return guarantee_given;
    }

    public void setGuarantee_given(int guarantee_given) {
        this.guarantee_given = guarantee_given;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
