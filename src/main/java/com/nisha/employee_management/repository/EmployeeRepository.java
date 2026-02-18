package com.nisha.employee_management.repository;

import com.nisha.employee_management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByNameContainingIgnoreCase(String name);
}
