package com.example.employeeservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employeeservice.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	Optional<Employee> findByEmail(String email);
	Optional<Employee> findByMobileNo1(String mobileNo1);
	List<Employee> findByOrgId(Integer ordId);
}
