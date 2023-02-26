package com.example.organizationservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrgOfficeLocation;
import com.example.organizationservice.service.OrganizationService;

@RestController
@RequestMapping(path="/api/v1/org")
@CrossOrigin(origins="*")
public class OrganizationController {
	
	Logger logger = LoggerFactory.getLogger(OrganizationController.class);
	
	private final OrganizationService orgService;
	
	@Autowired
	OrganizationController(OrganizationService orgService)
	{
		this.orgService = orgService;
	}

	@GetMapping
	public ResponseEntity<?> getAllOrganization()
	{
		logger.info("Returning all organizations");
		return new ResponseEntity<List<Organization>>(orgService.getAllOrganization(),HttpStatus.OK);
	}
	
	@GetMapping(path="/{orgId}")
	public ResponseEntity<?> getOrganizationById(@PathVariable Integer orgId)
	{
		try
		{
			return new ResponseEntity<Organization>(orgService.getOrganizationById(orgId),HttpStatus.OK);
		}
		catch(HandleCustomExceptions e)
		{
			return new ResponseEntity<HandleCustomExceptions>(e,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> addNewOrganization(@RequestBody Organization org)
	{
		try
		{
			return new ResponseEntity<Organization>(orgService.addNewOrganization(org),HttpStatus.CREATED);
		}
		catch(HandleCustomExceptions e)
		{
			return new ResponseEntity<HandleCustomExceptions>(e,HttpStatus.BAD_REQUEST);
		}
	
	}
	
	@PutMapping(path="/{orgId}")
	public ResponseEntity<?> updateOrganizationById(@PathVariable Integer orgId,@RequestBody Organization org)
	{
		try
		{
			return new ResponseEntity<Organization>(orgService.updateOrganizationById(orgId,org),HttpStatus.CREATED);
		}
		catch(HandleCustomExceptions e)
		{
			return new ResponseEntity<HandleCustomExceptions>(e,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path="/{orgId}")
	public ResponseEntity<?> removeOrganizationById(@PathVariable Integer orgId)
	{
		try
		{
			orgService.deleteOrganizationById(orgId);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
		catch(HandleCustomExceptions e)
		{
			return new ResponseEntity<HandleCustomExceptions>(e,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(path="/officelocation/add/{orgId}")
	public ResponseEntity<?> addNewAddressToOrganization(@PathVariable Integer orgId,@RequestBody OrgOfficeLocation orgAddress)
	{
		
		try
		{
			return new ResponseEntity<Organization>(orgService.addNewOrgOfficeLocation(orgId,orgAddress),HttpStatus.CREATED);
		}
		catch(HandleCustomExceptions e)
		{
			return new ResponseEntity<HandleCustomExceptions>(e,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(path="/officelocation/update/{addressId}")
	public ResponseEntity<?> updateOrgOfficeLocationById(@PathVariable Integer addressId,@RequestBody OrgOfficeLocation orgAddress)
	{
		try
		{
			return new ResponseEntity<OrgOfficeLocation>(orgService.updateOrgOfficeLocationById(addressId,orgAddress),HttpStatus.CREATED);
		}
		catch(HandleCustomExceptions e)
		{
			return new ResponseEntity<HandleCustomExceptions>(e,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path="/officelocation/delete/{addressId}")
	public ResponseEntity<?> deleteOrgOfficeLocationById(@PathVariable Integer addressId)
	{
		try
		{
			orgService.deleteOrgOfficeLocationById(addressId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(HandleCustomExceptions e)
		{
			return new ResponseEntity<HandleCustomExceptions>(e,HttpStatus.BAD_REQUEST);
		}
	}
}
