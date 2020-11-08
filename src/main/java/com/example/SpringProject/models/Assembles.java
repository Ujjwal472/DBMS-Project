package com.example.SpringProject.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Assembles {
    @Id
    @GeneratedValue
    private long assembles_id;
    private int day;
    private int month;
    private int year;

    private int count;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Override
    public String toString() {
        return "Assembles{" +
                "assembles_id=" + assembles_id +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", count=" + count +
                ", product=" + product +
                ", employee=" + employee +
                '}';
    }

    public long getAssembles_id() {
        return assembles_id;
    }

    public void setAssembles_id(long assembles_id) {
        this.assembles_id = assembles_id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
