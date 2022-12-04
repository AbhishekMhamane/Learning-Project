package com.example.employeeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.Organization;
import com.example.employeeservice.service.EmployeeService;

import custom_exception.HandleCustomException;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/api/v1/emp")
public class EmployeeController {
	
	private final EmployeeService empService;
	
	@Autowired
	EmployeeController(EmployeeService empService)
	{
		this.empService=empService;
	}
	
	@GetMapping(path="/{orgId}")
	ResponseEntity<?> getEmployee(@PathVariable Integer orgId)
	{	
		return new ResponseEntity<Employee>(empService.getEmployee(orgId),HttpStatus.OK);
	}
	
	@PostMapping(path="/{orgId}/{deptId}")
	ResponseEntity<?> addEmployee(@PathVariable("orgId") Integer orgId,@PathVariable("deptId") Integer deptId,@RequestBody Employee emp)
	{
		try
		{
			return new ResponseEntity<Employee>(empService.addEmployee(orgId,deptId,emp),HttpStatus.CREATED);
		}
		catch(HandleCustomException e)
		{
			return new ResponseEntity<HandleCustomException>(e,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	

}
