package com.example.SpringProject.controllers;

import com.example.SpringProject.Services.PartService;
import com.example.SpringProject.Services.ProductService;
import com.example.SpringProject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    PartService partService;

    @GetMapping("/addProduct")
    public ModelAndView addProductForm() {
        ModelAndView mv = new ModelAndView("addProductForm");
        mv.addObject("product", new Product());
        return mv;
    }

    @PostMapping("/addProduct")
    public ModelAndView addProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        redirectAttributes.addAttribute("product_id", product.getProduct_id());
        if (product.getProduct_id() != 0) {
            product.setAssembles(productService.getProductById(product.getProduct_id()).getAssembles());
            product.setParts_required(productService.getProductById(product.getProduct_id()).getParts_required());
        }
        product.setProductName(product.getProductName().toLowerCase());
        if (productService.checkByProductName(product.getProductName())) {
            redirectAttributes.addFlashAttribute("error", "product already exists");
            if (product.getProduct_id() == 0) mv.setViewName("redirect:/addProduct");
            else mv.setViewName("redirect:/productUpdateForm/{product_id}");
        } else {
            mv.setViewName("redirect:/products");
            productService.saveProduct(product);
        }
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
    public ModelAndView incrementDe(@PathVariable(name = "id") int product_id, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("redirect:/products");
        Product product = productService.getProductById(product_id);
        if (product.getTotal_defective() + 1 > product.getTotal_available()) {
            redirectAttributes.addFlashAttribute("error", "Defective count cannot exceed available count!");
        } else {
            product.setTotal_defective(product.getTotal_defective() + 1);
            productService.saveProduct(product);
        }
        return mv;
    }

    @GetMapping("/partsRequired/{id}")
    public ModelAndView getPartsRequired(@PathVariable(name = "id") int product_id) {
        ModelAndView mv = new ModelAndView("listPartsRequired");
        List<Part> parts_req = productService.getProductById(product_id).getParts_required();
        List<Part> all_parts = partService.getAllPart();
        mv.addObject("parts_req", parts_req);
        mv.addObject("all_parts", all_parts);
        mv.addObject("product", productService.getProductById(product_id));
        mv.addObject("part", new Part());
        return mv;
    }

    @PostMapping("/addPartsRequired/{id}")
    public ModelAndView addPartRequired(@ModelAttribute(name = "part") Part part, @PathVariable(name = "id") int product_id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("product_id", product_id);
        part = partService.getPartById(part.getPart_id());
        ModelAndView mv = new ModelAndView("redirect:/partsRequired/{product_id}");
        Product product = productService.getProductById(product_id);
        if (product.getParts_required().contains(part)) {
            redirectAttributes.addFlashAttribute("error", "part already exists");
        } else {
            product.getParts_required().add(part);
            part.getUsed_in().add(product);
            productService.saveProduct(product);
            partService.savePart(part);
        }
        return mv;
    }

    @GetMapping("/deletePartsRequirement/{product_id}/{part_id}")
    public ModelAndView deletePartRequirement(@PathVariable(name = "product_id") int product_id, @PathVariable(name = "part_id") int part_id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("product_id", product_id);
        ModelAndView mv = new ModelAndView("redirect:/partsRequired/{product_id}");
        Part part = partService.getPartById(part_id);
        Product product = productService.getProductById(product_id);

        product.getParts_required().remove(part);
        part.getUsed_in().remove(product);

        partService.savePart(part);
        productService.saveProduct(product);

        return mv;
    }

    @GetMapping("/generateProduct/{id}")
    public ModelAndView generateProductForm(@PathVariable(name = "id") int product_id) {
        ModelAndView mv = new ModelAndView("generateProductForm");
        ProductRequest pr = new ProductRequest();
        pr.setProduct_id(product_id);
        HashMap<String, String> temp = new HashMap<>();
        Product product = productService.getProductById(product_id);
        List<Part> parts_required = product.getParts_required();
        for (Part part: parts_required) temp.put(part.getPartName(), "0");
        pr.setPartsRequired(temp);
        CostWrapper cw = new CostWrapper();
        cw.setCost(-1);
        Status status = new Status();
        status.setStatus(-1);
        mv.addObject("partsWrapper", pr);
        mv.addObject("product_cost", cw);
        mv.addObject("status", status);
        return mv;
    }

    @GetMapping("/getCost")
    public ModelAndView getCost(@ModelAttribute(name = "partsWrapper") ProductRequest pr, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("part_id", pr.getProduct_id());
        ModelAndView mv = new ModelAndView();
        HashMap<String, String> parts = pr.getPartsRequired();
        int total_cost = 0;
        Status status = new Status();
        status.setStatus(1);
        for (Map.Entry<String, String> mapElement: parts.entrySet()) {
            int units_required = Integer.parseInt(mapElement.getValue());
            if (units_required < 0) {
                redirectAttributes.addFlashAttribute("error", "Please enter non negative integral number of units");
                mv.setViewName("redirect:/generateProduct/{product_id}");
                return mv;
            }
            if (units_required > partService.getPartByName(mapElement.getKey()).getTotal_available()) status.setStatus(0);
            total_cost += partService.getPartByName(mapElement.getKey()).getTotal_material_cost() * units_required;
        }
        mv.setViewName("generateProductForm");
        CostWrapper cw = new CostWrapper();
        cw.setCost(total_cost);
        mv.addObject("product_cost", cw);
        mv.addObject("partsWrapper", pr);
        mv.addObject("status", status);
        System.out.println("reached here atleast!");
        return mv;
    }


}
