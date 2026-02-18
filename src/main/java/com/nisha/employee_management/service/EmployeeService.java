package com.nisha.employee_management.service;

import com.nisha.employee_management.model.Employee;
import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(int id);

    void deleteEmployee(int id);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(int id, Employee employee);

    List<Employee> searchByName(String name);
    List<Employee> sortEmployees(String field);


}

