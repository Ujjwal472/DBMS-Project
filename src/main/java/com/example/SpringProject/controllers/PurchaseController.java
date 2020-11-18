package com.example.SpringProject.controllers;

import com.example.SpringProject.Services.CustomerService;
import com.example.SpringProject.Services.DateService;
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

import java.text.ParseException;
import java.util.List;

@Controller
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    DateService dateService;

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
    public ModelAndView addPurchase(@ModelAttribute(name = "purchase") Purchase purchase, RedirectAttributes redirectAttributes) throws ParseException {
        redirectAttributes.addAttribute("customer_id", purchase.getCustomer().getCustomer_id());
        redirectAttributes.addAttribute("purchase_id", purchase.getPurchase_id());
        ModelAndView mv = new ModelAndView("redirect:/purchases/{customer_id}");
        purchase.setProduct(productService.getProductById(purchase.getProduct().getProduct_id()));
        if (!dateService.isValid(purchase.getPurchase_day(), purchase.getPurchase_month(), purchase.getPurchase_year())) {
            redirectAttributes.addFlashAttribute("error", "please enter a valid purchase date");
            if (purchase.getPurchase_id() == 0) mv.setViewName("redirect:/addPurchase/{customer_id}");
            else mv.setViewName("redirect:/purchaseUpdateForm/{purchase_id}");
            return mv;
        } else {
            String purchase_date = dateService.convert(purchase.getPurchase_day(), purchase.getPurchase_month(), purchase.getPurchase_year());
            if (purchase.getDelivery_status().equals("Y")) {
                if (!dateService.isValid(purchase.getDelivery_day(), purchase.getDelivery_month(), purchase.getDelivery_year())) {
                    redirectAttributes.addFlashAttribute("error", "please enter a valid delivery date");
                    if (purchase.getPurchase_id() == 0) mv.setViewName("redirect:/addPurchase/{customer_id}");
                    else mv.setViewName("redirect:/purchaseUpdateForm/{purchase_id}");
                    return mv;
                }
                String delivery_date = dateService.convert(purchase.getDelivery_day(), purchase.getDelivery_month(), purchase.getDelivery_year());
                if (dateService.compare(purchase_date, delivery_date) > 0) {
                    redirectAttributes.addFlashAttribute("error", "delivery date should come after purchase date, please check!");
                    if (purchase.getPurchase_id() == 0) mv.setViewName("redirect:/addPurchase/{customer_id}");
                    else mv.setViewName("redirect:/purchaseUpdateForm/{purchase_id}");
                    return mv;
                }
            }
        }
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
