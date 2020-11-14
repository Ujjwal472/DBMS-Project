package com.example.SpringProject.controllers;

import com.example.SpringProject.Services.ProductService;
import com.example.SpringProject.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/addProduct")
    public ModelAndView addProductForm() {
        ModelAndView mv = new ModelAndView("addProductForm");
        mv.addObject("product", new Product());
        return mv;
    }

    @PostMapping("/addProduct")
    public ModelAndView addProduct(@ModelAttribute Product product) {
        ModelAndView mv = new ModelAndView("redirect:/products");
        productService.saveProduct(product);
        return mv;
    }

    @GetMapping("/products")
    public ModelAndView productList() {
        ModelAndView mv = new ModelAndView("listProducts");
        List<Product> all_products = productService.getAllProduct();
        mv.addObject("all_products", all_products);
        return mv;
    }

    @GetMapping("/productUpdateForm/{id}")
    public ModelAndView productUpdateForm(@PathVariable(name = "id") int product_id) {
        ModelAndView mv = new ModelAndView("productUpdateForm");
        Product product = productService.getProductById(product_id);
        mv.addObject("product", product);
        return mv;
    }

    @GetMapping("/deleteProduct/{id}")
    public ModelAndView deleteProduct(@PathVariable(name = "id") int product_id) {
        ModelAndView mv = new ModelAndView("redirect:/products");
        productService.deleteProductById(product_id);
        return mv;
    }

    @GetMapping("/decrementProductAvailable/{id}")
    public ModelAndView decrementAv(@PathVariable(name = "id") int product_id) {
        ModelAndView mv = new ModelAndView("redirect:/products");
        Product product = productService.getProductById(product_id);
        int cnt = product.getTotal_available();
        if (cnt > 0) product.setTotal_available(cnt - 1);
        productService.saveProduct(product);
        return mv;
    }

    @GetMapping("/incrementProductAvailable/{id}")
    public ModelAndView incrementAv(@PathVariable(name = "id") int product_id) {
        ModelAndView mv = new ModelAndView("redirect:/products");
        Product product = productService.getProductById(product_id);
        product.setTotal_available(product.getTotal_available() + 1);
        productService.saveProduct(product);
        return mv;
    }

    @GetMapping("/decrementProductDefective/{id}")
    public ModelAndView decrementDe(@PathVariable(name = "id") int product_id) {
        ModelAndView mv = new ModelAndView("redirect:/products");
        Product product = productService.getProductById(product_id);
        int cnt = product.getTotal_defective();
        if (cnt > 0) product.setTotal_defective(cnt - 1);
        productService.saveProduct(product);
        return mv;
    }

    @GetMapping("/incrementProductDefective/{id}")
    public ModelAndView incrementDe(@PathVariable(name = "id") int product_id) {
        ModelAndView mv = new ModelAndView("redirect:/products");
        Product product = productService.getProductById(product_id);
        product.setTotal_defective(product.getTotal_defective() + 1);
        productService.saveProduct(product);
        return mv;
    }

}
