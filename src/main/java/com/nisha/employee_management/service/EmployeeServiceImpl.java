package com.nisha.employee_management.service;

import com.nisha.employee_management.exception.EmployeeNotFoundException;
import com.nisha.employee_management.model.Employee;
import com.nisha.employee_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    @Override
    public List<Employee> sortEmployees(String field) {
        return employeeRepository.findAll(Sort.by(field).ascending());
    }
    @Override
    public Employee addEmployee(Employee employee) {
        // save() will insert employee into database
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }
    @Override
    public List<Employee> searchByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }




    @Override
    public Employee updateEmployee(int id, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id).orElse(null);

        if (existingEmployee == null) {
            return null;
        }

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(int id) {
        // Only delete if employee exists
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        }

    }
}
