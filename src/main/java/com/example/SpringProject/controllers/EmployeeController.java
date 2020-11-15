package com.example.SpringProject.controllers;

import com.example.SpringProject.Services.DateService;
import com.example.SpringProject.Services.EmployeeService;
import com.example.SpringProject.models.Employee;
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
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DateService dateService;

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
    public ModelAndView addEmployee(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        redirectAttributes.addAttribute("employee_id", employee.getEmployee_id());
        if (!dateService.isValid(employee.getJoining_day(), employee.getJoining_month(), employee.getJoining_year())) {
            redirectAttributes.addFlashAttribute("error", "Please enter a valid joining date");
            if (employee.getEmployee_id() == 0) mv.setViewName("redirect:/addEmployee");
            else mv.setViewName("redirect:/employeeUpdateForm/{employee_id}");
        } else if (employeeService.checkByAadhaarNumber(employee.getAadhaarNumber()) && employeeService.getByAadhaarNumber(employee.getAadhaarNumber()).getEmployee_id() != employee.getEmployee_id()) {
            redirectAttributes.addFlashAttribute("error", "Aadhaar number already exists please check!");
            if (employee.getEmployee_id() == 0) mv.setViewName("redirect:/addEmployee");
            else mv.setViewName("redirect:/employeeUpdateForm/{employee_id}");
        } else {
            this.employeeService.saveEmployee(employee);
            mv.setViewName("redirect:/employees");
        }
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
