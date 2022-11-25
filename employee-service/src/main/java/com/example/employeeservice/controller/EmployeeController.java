package com.example.employeeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import com.example.employeeservice.custom_exceptions.HandleCustomException;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.Organization;
import com.example.employeeservice.service.EmployeeService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/api/v1/emp")
public class EmployeeController {
	
	private final EmployeeService empService;
	
	@Autowired
	EmployeeController(EmployeeService empService)
	{
		this.empService = empService;
	}
	
	@GetMapping(path="/org/{orgId}")
	public ResponseEntity<?> getAllEmployeeByOrgId(@PathVariable("orgId") Integer orgId)
	{
		try
		{
			return new ResponseEntity<List<Employee>>(empService.getAllEmployeeByOrgId(orgId),HttpStatus.OK);
		}
		catch(HandleCustomException e)
		{
			return new ResponseEntity<HandleCustomException>(e,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(path="/{empId}")
	public ResponseEntity<?> getEmployeeByEmpId(@PathVariable("empId") Integer empId)
	{
		try
		{
			return new ResponseEntity<Employee>(empService.getEmployeeByEmpId(empId),HttpStatus.OK);
		}
		catch(HandleCustomException e)
		{
			return new ResponseEntity<HandleCustomException>(e,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@PostMapping(path="/{orgId}/{deptId}")
	public ResponseEntity<?> addEmployeeToOrganization(@PathVariable("orgId") Integer orgId,@PathVariable("deptId") Integer deptId,
			@RequestBody Employee emp)
	{
		try
		{
			return new ResponseEntity<Employee>(empService.addEmployeeToOrganization(orgId,deptId,emp),HttpStatus.CREATED);
		}
		catch(HandleCustomException e)
		{
			return new ResponseEntity<HandleCustomException>(e,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@PutMapping(path="/{empId}")
	public ResponseEntity<?> updateEmployeeByEmpId(@PathVariable("empId") Integer empId,@RequestBody Employee emp)
	{
		try
		{
			return new ResponseEntity<Employee>(empService.updateEmployeeByEmpId(empId,emp),HttpStatus.OK);
		}
		catch(HandleCustomException e)
		{
			return new ResponseEntity<HandleCustomException>(e,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping(path="/{empId}")
	public ResponseEntity<?> deleteEmployeeByEmpId(@PathVariable("empId") Integer empId)
	{
		try
		{
			empService.deleteEmployeeByEmpId(empId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(HandleCustomException e)
		{
			return new ResponseEntity<HandleCustomException>(e,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
//	@Autowired
//	private RestTemplate restTemplate;
//	
//	String orgUrl = "http://organization-service/org/api";
//	
//	@GetMapping
//	ResponseEntity<?> getEmployee()
//	{
//		Employee emp = new Employee(1,"abhishek mhamane");
//		
//		Organization org = this.restTemplate.getForObject(orgUrl, Organization.class);
//
//		emp.setOrg(org);
//		
//		return new ResponseEntity<Employee>(new Employee(),HttpStatus.OK);
//	}

}
