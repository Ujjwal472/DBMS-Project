package com.example.SpringProject.Services;

import com.example.SpringProject.models.Employee;
import com.example.SpringProject.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    public Employee getEmployeeById(int employee_id) {
        Optional<Employee> optional = employeeRepository.findById(employee_id);
        Employee employee;
        if (optional.isPresent()) employee = optional.get();
        else throw new RuntimeException("No Employee with id = " + employee_id);
        return employee;
    }

    public List<Employee> getAllEmployee() {
        List<Employee> all_employee = employeeRepository.findAll();
        return all_employee;
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployeeById(int employee_id) {
        employeeRepository.deleteById(employee_id);
    }

    public boolean checkByAadhaarNumber(long aadhaar_number) {
        Optional<Employee> optional = employeeRepository.findByAadhaarNumber(aadhaar_number);
        return optional.isPresent();
    }

    public Employee getByAadhaarNumber(long aadhaar_number) {
        Optional<Employee> optional = employeeRepository.findByAadhaarNumber(aadhaar_number);
        if (optional.isPresent()) return optional.get();
        throw new RuntimeException("No employee having aadhaar number = " + aadhaar_number);
    }

}
