package com.example.organizationservice.controller;


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

import com.example.organizationservice.custom_exceptions.HandleCustomExceptions;
import com.example.organizationservice.model.Department;
import com.example.organizationservice.service.DepartmentService;

@RestController
@RequestMapping(path="/api/v1/dept")
@CrossOrigin(origins="*")
public class DepartmentController {
	
	private final DepartmentService deptService;
	
	@Autowired
	DepartmentController(DepartmentService deptService)
	{
		this.deptService = deptService;
	}

	@GetMapping(path="/{deptId}")
	public ResponseEntity<?> getDepartmentsByDepartmentId(@PathVariable Integer deptId)
	{
		try
		{
			return new ResponseEntity<Department>(deptService.getDepartmentsByDepartmentId(deptId),HttpStatus.OK);			
		}
		catch(HandleCustomExceptions e)
		{
			return new ResponseEntity<HandleCustomExceptions>(e,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping(path="/{orgId}")
	public ResponseEntity<?> createDepartmentsByOrgId(@PathVariable Integer orgId,@RequestBody Department dept)
	{
		try
		{
			return new ResponseEntity<Department>(deptService.createDepartmentsByOrgId(orgId,dept),HttpStatus.OK);			
		}
		catch(HandleCustomExceptions e)
		{
			return new ResponseEntity<HandleCustomExceptions>(e,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(path="/{deptId}")
	public ResponseEntity<?> updateDepartmentsByDepartmentId(@PathVariable Integer deptId,@RequestBody Department dept)
	{
		try
		{
			return new ResponseEntity<Department>(deptService.updateDepartmentsByDepartmentId(deptId,dept),HttpStatus.OK);			
		}
		catch(HandleCustomExceptions e)
		{
			return new ResponseEntity<HandleCustomExceptions>(e,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path="/{deptId}")
	public ResponseEntity<?> deleteDepartmentsByDepartmentId(@PathVariable Integer deptId)
	{
		try
		{
			deptService.deleteDepartmentsByDepartmentId(deptId);
			return new ResponseEntity<>(HttpStatus.OK);			
		}
		catch(HandleCustomExceptions e)
		{
			return new ResponseEntity<HandleCustomExceptions>(e,HttpStatus.NO_CONTENT);
		}
	}
}
