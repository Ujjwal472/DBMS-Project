package com.example.SpringProject.controllers;

import com.example.SpringProject.Services.CustomerService;
import com.example.SpringProject.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public ModelAndView customerList() {
        ModelAndView mv = new ModelAndView("listCustomers");
        List<Customer> all_customers = customerService.getAllCustomer();
        mv.addObject("all_customers", all_customers);
        return mv;
    }

    @GetMapping("/addCustomer")
    public ModelAndView addCustomerForm() {
        ModelAndView mv = new ModelAndView("addCustomerForm");
        Customer customer = new Customer();
        mv.addObject("customer", customer);
        return mv;
    }

    @PostMapping("/addCustomer")
    public ModelAndView addCustomer(@ModelAttribute(name = "customer") Customer customer) {
        ModelAndView mv = new ModelAndView("redirect:/customers");
        if (customer.getCustomer_id() != 0) customer.setPurchases(customerService.getCustomerById(customer.getCustomer_id()).getPurchases());
        if (customer.getOffice_contact() == null) customer.setOffice_contact("-");
        customerService.saveCustomer(customer);
        return mv;
    }

    @GetMapping("/customerUpdateForm/{id}")
    public ModelAndView customerUpdateForm(@PathVariable(name = "id") int customer_id) {
        System.out.println("Entered here!");
        ModelAndView mv = new ModelAndView("customerUpdateForm");
        Customer customer = customerService.getCustomerById(customer_id);
        mv.addObject("customer", customer);
        return mv;
    }

    @GetMapping("/deleteCustomer/{id}")
    public ModelAndView deleteCustomer(@PathVariable(name = "id") int customer_id) {
        ModelAndView mv = new ModelAndView("redirect:/customers");
        System.out.println("Deleting a customer");
        customerService.deleteCustomerById(customer_id);
        System.out.println("Customer Deleted");
        return mv;
    }
}
