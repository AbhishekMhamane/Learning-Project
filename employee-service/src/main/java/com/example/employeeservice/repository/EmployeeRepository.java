package com.example.employeeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.employeeservice.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	
//	@Query(nativeQuery=true,value="select * from employee where org_id = :oid")
//	List<Employee> findByOrgId(@Param("oid") Integer orgId);
	
	List<Employee> findByOrgId(Integer orgId);

}
