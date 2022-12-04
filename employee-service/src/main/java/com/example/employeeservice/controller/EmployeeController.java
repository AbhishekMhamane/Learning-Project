package com.example.employeeservice.controller;

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
import java.util.*;
import com.example.employeeservice.model.Employee;
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
	
	@GetMapping
	ResponseEntity<?> getAllEmployee()
	{
		try
		{
			return new ResponseEntity<List<Employee>>(empService.getAllEmployee(),HttpStatus.OK);
		}
		catch(HandleCustomException e)
		{
			return new ResponseEntity<HandleCustomException>(e,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(path="/add/{orgId}/{deptId}")
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
	
	
	
	@GetMapping(path="/org/{orgId}")
	ResponseEntity<?> getAllEmployeesByOrg(@PathVariable Integer orgId)
	{	
		return new ResponseEntity<List<Employee>>(empService.getAllEmployeesByOrg(orgId),HttpStatus.OK);
	}
	
	@GetMapping(path="/get/{empId}")
	ResponseEntity<?> getEmployeeById(@PathVariable Integer empId)
	{
		try
		{
			return new ResponseEntity<Employee>(empService.getEmployeeById(empId),HttpStatus.OK);
		}
		catch(HandleCustomException e)
		{
			return new ResponseEntity<HandleCustomException>(e,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(path="/update/{empId}")
	ResponseEntity<?> updateEmployeeById(@PathVariable Integer empId,@RequestBody Employee emp)
	{
		try
		{
			return new ResponseEntity<Employee>(empService.updateEmployeeById(empId,emp),HttpStatus.OK);
		}
		catch(HandleCustomException e)
		{
			return new ResponseEntity<HandleCustomException>(e,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path="/delete/{empId}")
	ResponseEntity<?> deleteEmployeeById(@PathVariable Integer empId)
	{
		try
		{
			empService.deletetEmployeeById(empId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(HandleCustomException e)
		{
			return new ResponseEntity<HandleCustomException>(e,HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
