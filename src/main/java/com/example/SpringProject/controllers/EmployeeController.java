package com.example.SpringProject.controllers;

import com.example.SpringProject.Services.EmployeeService;
import com.example.SpringProject.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }

    @GetMapping("/addEmployee")
    public ModelAndView addEmployeeForm() {
        ModelAndView mv = new ModelAndView("addEmployeeForm");
        mv.addObject("employee", new Employee());
        return mv;
    }

    @PostMapping("/addEmployee")
    public ModelAndView addEmployee(@ModelAttribute Employee employee) {
        ModelAndView mv = new ModelAndView("redirect:/employees");
        this.employeeService.saveEmployee(employee);
        return mv;
    }

    @GetMapping("/employees")
    public ModelAndView employeeList() {
        ModelAndView mv = new ModelAndView("listEmployees");
        List<Employee> all_employees = employeeService.getAllEmployee();
        mv.addObject("all_employees", all_employees);
        return mv;
    }

    @GetMapping("/employeeUpdateForm/{id}")
    public ModelAndView employeeUpdateForm(@PathVariable(value = "id") int employee_id) {
        ModelAndView mv = new ModelAndView("employeeUpdateForm");
        Employee employee = employeeService.getEmployeeById(employee_id);
        mv.addObject("employee", employee);
        return mv;
    }

    @GetMapping("/deleteEmployee/{id}")
    public ModelAndView deleteEmployee(@PathVariable(value = "id") int employee_id) {
        ModelAndView mv = new ModelAndView("redirect:/employees");
        employeeService.deleteEmployeeById(employee_id);
        return mv;
    }

}
