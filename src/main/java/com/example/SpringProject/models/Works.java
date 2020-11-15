package com.example.SpringProject.models;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class Works {
    @Id
    @GeneratedValue
    private int works_id;
    private int day;
    private int month;
    private int year;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;

    public int getWorks_id() {
        return works_id;
    }

    public void setWorks_id(int works_id) {
        this.works_id = works_id;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
