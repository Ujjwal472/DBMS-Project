package com.example.SpringProject.controllers;

import com.example.SpringProject.Services.CustomerService;
import com.example.SpringProject.Services.ProductService;
import com.example.SpringProject.Services.PurchaseService;
import com.example.SpringProject.models.Customer;
import com.example.SpringProject.models.Product;
import com.example.SpringProject.models.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @GetMapping("/purchases/{id}")
    public ModelAndView listPurchase(@PathVariable(name = "id") int customer_id) {
        ModelAndView mv = new ModelAndView("listPurchases");
        List<Purchase> all_purchases = purchaseService.getPurchaseByCustomerId(customer_id);
        Customer customer = customerService.getCustomerById(customer_id);
        mv.addObject("customer", customer);
        mv.addObject("all_purchases", all_purchases);
        return mv;
    }

    @GetMapping("/addPurchase/{id}")
    public ModelAndView addPurchaseForm(@PathVariable(name = "id") int customer_id) {
        ModelAndView mv = new ModelAndView("addPurchaseForm");
        Purchase purchase = new Purchase();
        purchase.setCustomer(customerService.getCustomerById(customer_id));
        purchase.setProduct(new Product());
        List<Product> all_products = productService.getAllProduct();
        mv.addObject("all_products", all_products);
        mv.addObject("purchase", purchase);
        mv.addObject("customer", customerService.getCustomerById(customer_id));
        return mv;
    }

    @PostMapping("/addPurchase")
    public ModelAndView addPurchase(@ModelAttribute(name = "purchase") Purchase purchase, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("customer_id", purchase.getCustomer().getCustomer_id());
        ModelAndView mv = new ModelAndView("redirect:/purchases/{customer_id}");
        purchase.setProduct(productService.getProductById(purchase.getProduct().getProduct_id()));
//        purchase.setCustomer(customerService.getCustomerById(customer_id));
        purchaseService.savePurchase(purchase);
        return mv;
    }

    @GetMapping("/purchaseUpdateForm/{id}")
    public ModelAndView updatePurchase(@PathVariable(name = "id") long purchase_id) {
        ModelAndView mv = new ModelAndView("purchaseUpdateForm");
        Purchase purchase = purchaseService.getPurchaseById(purchase_id);
        mv.addObject("all_products", productService.getAllProduct());
        mv.addObject("customer", purchase.getCustomer());
        mv.addObject("purchase", purchase);
        return mv;
    }

    @GetMapping("/deletePurchase/{id}")
    public ModelAndView deletePurchase(@PathVariable(name = "id") long purchase_id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("customer_id", purchaseService.getPurchaseById(purchase_id).getCustomer().getCustomer_id());
        ModelAndView mv = new ModelAndView("redirect:/purchases/{customer_id}");
        purchaseService.deleteById(purchase_id);
        return mv;
    }
}
