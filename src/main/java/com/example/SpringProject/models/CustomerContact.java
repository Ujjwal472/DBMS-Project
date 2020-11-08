package com.example.SpringProject.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(Contact.class)
public class CustomerContact {
    @Id
    private String phone;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
