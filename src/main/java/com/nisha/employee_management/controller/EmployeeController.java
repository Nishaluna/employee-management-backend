package com.nisha.employee_management.controller;



import com.nisha.employee_management.model.Employee;
import com.nisha.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }


    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable int id, @Valid @RequestBody Employee employee) {

        Employee updatedEmployee = employeeService.updateEmployee(id, employee);

        if (updatedEmployee != null) {
            return "Employee updated successfully!";
        }

        return "Employee not found!";
    }
    // Search employees by name
    @GetMapping("/search")
    public List<Employee> searchEmployees(
            @RequestParam(required = false) String name
           ) {

        if (name != null) {
            return employeeService.searchByName(name);
        }



        // If no query parameter provided, return all employees
        return employeeService.getAllEmployees();
    }




    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {

        Employee existingEmployee = employeeService.getEmployeeById(id);

        if (existingEmployee != null) {
            employeeService.deleteEmployee(id);
            return "Employee deleted successfully!";
        }

        return "Employee not found!";
    }
}

