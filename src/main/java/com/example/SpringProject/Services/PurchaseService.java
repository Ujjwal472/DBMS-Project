package com.example.SpringProject.Services;

import com.example.SpringProject.models.Customer;
import com.example.SpringProject.models.Purchase;
import com.example.SpringProject.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    CustomerService customerService;

    public List<Purchase> getPurchaseByCustomerId(int customer_id) {
        Customer customer = customerService.getCustomerById(customer_id);
        return purchaseRepository.findByCustomer(customer);
    }

    public void savePurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    public Purchase getPurchaseById(long purchase_id) {
        Optional<Purchase> optional = purchaseRepository.findById(purchase_id);
        if (optional.isPresent()) return optional.get();
        else throw new RuntimeException("No record of such a purchase");
    }

    public void deleteById(long purchase_id) {
        purchaseRepository.deleteById(purchase_id);
    }
}
