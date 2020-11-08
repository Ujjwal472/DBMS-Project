package com.example.SpringProject.controllers;

import com.example.SpringProject.Services.AssemblesService;
import com.example.SpringProject.Services.EmployeeService;
import com.example.SpringProject.Services.ProductService;
import com.example.SpringProject.models.Assembles;
import com.example.SpringProject.models.Employee;
import com.example.SpringProject.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.TreeMap;

@Controller
public class AssemblesController {
    @Autowired
    AssemblesService assemblesService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ProductService productService;

    @GetMapping("getAssembles/{id}")
    public ModelAndView employeeAssembles(@PathVariable(name = "id") int employee_id) {
        // create a modelAndView object with the view name passed to the constructor
        ModelAndView mv = new ModelAndView("listEmployeeAssembles");

        // list of all assembles objects for the employee with employee_id
        List<Assembles> all_assembles = assemblesService.getAssemblesByEmployeeId(employee_id);

        // To retrieve the name of the calling employee in the template
        Employee employee = employeeService.getEmployeeById(employee_id);

        // get a list of all the products
        List<Product> all_products = productService.getAllProduct();

        // map all the product names to their product ids for quick access in the view
        TreeMap<Integer, String> product_name_mapping = new TreeMap<Integer, String>();
        for (Product product : all_products) {
            product_name_mapping.put(product.getProduct_id(), product.getProduct_name());
        }

        // initialize the modelAndView object with all the variables required in the template
        mv.addObject("product_name_mapping", product_name_mapping);
        mv.addObject("employee", employee);
        mv.addObject("all_assembles", all_assembles);

        return mv;
    }

    @GetMapping("/addAssembles/{id}")
    public ModelAndView addAssemblesForm(@PathVariable(name = "id") int employee_id) {
        ModelAndView mv = new ModelAndView("addAssemblesForm");
        Assembles assembles = new Assembles();
        assembles.setProduct(new Product());
        assembles.setEmployee(employeeService.getEmployeeById(employee_id));
        List<Product> all_products = productService.getAllProduct();
        mv.addObject("assembles", assembles);
        mv.addObject("all_products", all_products);
        return mv;
    }

    @PostMapping("/addAssembles")
    public ModelAndView addAssembles(@ModelAttribute("assembles") Assembles assembles, RedirectAttributes redirectAttributes) {
        // redirect to the employee's assembles table after saving the new instance
        System.out.println("Reached here!");
        redirectAttributes.addAttribute("employee_id", assembles.getEmployee().getEmployee_id());
        System.out.println("Reached here! employee id = " + assembles.getEmployee().getEmployee_id());
        ModelAndView mv = new ModelAndView("redirect:/getAssembles/{employee_id}");
        int pid = assembles.getProduct().getProduct_id();
        assembles.setProduct(productService.getProductById(pid));
        int eid = assembles.getEmployee().getEmployee_id();
        assembles.setEmployee(employeeService.getEmployeeById(eid));
        System.out.println(assembles.toString());
        assemblesService.saveAssembles(assembles);
        return mv;
    }

    @GetMapping("/deleteAssembles/{id}")
    public ModelAndView deleteAssembles(@PathVariable(name = "id") long assembles_id, RedirectAttributes redirectAttributes) {
        Assembles assembles = assemblesService.getAssemblesById(assembles_id);
        redirectAttributes.addAttribute("employee_id", assembles.getEmployee().getEmployee_id());
        ModelAndView mv = new ModelAndView("redirect:/getAssembles/{employee_id}");
        assemblesService.deleteByAssemblesId(assembles_id);
        return mv;
    }

}
