package com.example.employeeservice.repository;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.Organization;
import com.example.employeeservice.model.Department;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  List<Employee> findByOrg(Organization org);

  List<Employee> findByDept(Department department);
  
}
