package com.example.SpringProject.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
public class Employee {
    @Id
    @GeneratedValue
    private int employee_id;
    private String name;
    private long monthly_pay;
    private long aadhaarNumber;
    private int joining_day;
    private int joining_month;
    private int joining_year;
    private String address;

    @OneToMany(mappedBy = "employee", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Assembles> assembles;

    @OneToMany(mappedBy = "employee", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Works> works;

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMonthly_pay() {
        return monthly_pay;
    }

    public void setMonthly_pay(long monthly_pay) {
        this.monthly_pay = monthly_pay;
    }

    public long getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(long aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public int getJoining_day() {
        return joining_day;
    }

    public void setJoining_day(int joining_day) {
        this.joining_day = joining_day;
    }

    public int getJoining_month() {
        return joining_month;
    }

    public void setJoining_month(int joining_month) {
        this.joining_month = joining_month;
    }

    public int getJoining_year() {
        return joining_year;
    }

    public void setJoining_year(int joining_year) {
        this.joining_year = joining_year;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Assembles> getAssembles() {
        return assembles;
    }

    public void setAssembles(List<Assembles> assembles) {
        this.assembles = assembles;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", name='" + name + '\'' +
                ", monthly_pay=" + monthly_pay +
                ", aadhaarNumber=" + aadhaarNumber +
                ", joining_day=" + joining_day +
                ", joining_month=" + joining_month +
                ", joining_year=" + joining_year +
                ", address='" + address + '\'' +
                ", assembles=" + assembles +
                '}';
    }
}
