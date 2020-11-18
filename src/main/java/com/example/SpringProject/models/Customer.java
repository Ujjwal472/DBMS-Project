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
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private int customer_id;
    private String customer_name;
    private String email;
    private String company_name;
    private String GST_number;
    private String personal_contact;
    private String office_contact;

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<Purchase> purchases;

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getGST_number() {
        return GST_number;
    }

    public void setGST_number(String GST_number) {
        this.GST_number = GST_number;
    }

    public String getPersonal_contact() {
        return personal_contact;
    }

    public void setPersonal_contact(String personal_contact) {
        this.personal_contact = personal_contact;
    }

    public String getOffice_contact() {
        return office_contact;
    }

    public void setOffice_contact(String office_contact) {
        this.office_contact = office_contact;
    }
}
