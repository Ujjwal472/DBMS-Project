package com.example.SpringProject.Services;

import com.example.SpringProject.models.Customer;
import com.example.SpringProject.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomer() {
        List<Customer> all_customers = customerRepository.findAll();
        return all_customers;
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer getCustomerById(int customer_id) {
        Optional<Customer> optional = customerRepository.findById(customer_id);
        Customer customer;
        if (optional.isPresent()) customer = optional.get();
        else throw new RuntimeException("No Customer with id = " + customer_id);
        return customer;
    }

    public void deleteCustomerById(int customer_id) {
        System.out.println("customer id = " + customer_id);
        customerRepository.deleteById(customer_id);
    }
}
