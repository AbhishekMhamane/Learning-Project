package com.example.employeeservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeservice.custom_exceptions.HandleCustomException;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository empRepo;
	
	@Autowired
	EmployeeService(EmployeeRepository empRepo)
	{
		this.empRepo = empRepo;
	}
	

	public List<Employee> getAllEmployeeByOrgId(Integer orgId) {
		try
		{
			return empRepo.findByOrgId(orgId);
		}
		catch(Exception e)
		{
			throw new HandleCustomException("400",e.getMessage());
		}
		
	}
	

	public Employee getEmployeeByEmpId(Integer empId) {
		try
		{
			Optional<Employee> empRecord = empRepo.findById(empId);
			
			if(empRecord.isPresent())
			{
				return empRecord.get();
			}
			else
			{
				throw new HandleCustomException("400","Employee not present by given emp id");
			}
		}
		catch(HandleCustomException e)
		{
		   throw new  HandleCustomException(e.getErrorCode(),e.getErrorMessage());
		}
		catch(Exception e)
		{
			throw new HandleCustomException("400",e.getMessage());
		}
	}


	public Employee addEmployeeToOrganization(Integer orgId, Integer deptId, Employee emp) {
		
		try
		{
			//not added checking of org and dept is present or not
			
			//System.out.println(emp);
			
			if(emp.getFirstName() != null && emp.getLastName()!=null && emp.getEmail()!=null && emp.getMobileNo1()!=null)
			{
				Optional<Employee> empPresentByEmail = empRepo.findByEmail(emp.getEmail());
				Optional<Employee> empPresentByMob = empRepo.findByMobileNo1(emp.getMobileNo1());
				
				if(!empPresentByEmail.isPresent() && !empPresentByMob.isPresent())
				{
					emp.setOrgId(orgId);
					emp.setDeptId(deptId);
					
					return empRepo.save(emp);
				}
				else
				{
					throw new HandleCustomException("400","Employee already present with email & mobile no");
				}
			}
			else
			{
				throw new HandleCustomException("400","Please enter valid inputs !");
			}
			
		}
		catch(HandleCustomException e)
		{
		   throw new  HandleCustomException(e.getErrorCode(),e.getErrorMessage());
		}
		catch(Exception e)
		{
			throw new HandleCustomException("400",e.getMessage());
		}
		
	}


	public Employee updateEmployeeByEmpId(Integer empId, Employee emp) {
		try
		{
			Optional<Employee> empOptional = empRepo.findById(empId);
			
			if(empOptional.isPresent())
			{
				Employee empRecord = empOptional.get();
				
				if(emp.getFirstName()!=null && !emp.getFirstName().equals(empRecord.getFirstName()))
				{
					empRecord.setFirstName(emp.getFirstName());
				}
				
				if(emp.getLastName()!=null && !emp.getLastName().equals(empRecord.getLastName()))
				{
					empRecord.setLastName(emp.getLastName());
				}
				
				if(emp.getEmail()!=null && !emp.getEmail().equals(empRecord.getEmail()))
				{
					empRecord.setEmail(emp.getEmail());
				}
				
				if(emp.getMobileNo1()!=null && !emp.getMobileNo1().equals(empRecord.getMobileNo1()))
				{
					empRecord.setMobileNo1(emp.getMobileNo1());
				}
				
				if(emp.getMobileNo2()!=null && !emp.getMobileNo2().equals(empRecord.getMobileNo2()))
				{
					empRecord.setMobileNo2(emp.getMobileNo2());
				}
				
				if(emp.getDeptId()!=null && emp.getDeptId()!=empRecord.getDeptId())
				{
					empRecord.setDeptId(emp.getDeptId());
				}
				
				return empRepo.save(empRecord);
			}
			else
			{
				throw new HandleCustomException("400","Employee not present by given emp id");
			}
		}
		catch(HandleCustomException e)
		{
		   throw new  HandleCustomException(e.getErrorCode(),e.getErrorMessage());
		}
		catch(Exception e)
		{
			throw new HandleCustomException("400",e.getMessage());
		}
	}


	public void deleteEmployeeByEmpId(Integer empId) {
		try
		{
			Optional<Employee> empRecord = empRepo.findById(empId);
			
			if(empRecord.isPresent())
			{
				empRepo.deleteById(empId);
			}
			else
			{
				throw new HandleCustomException("400","Employee not present by given emp id");
			}
		}
		catch(HandleCustomException e)
		{
		   throw new  HandleCustomException(e.getErrorCode(),e.getErrorMessage());
		}
		catch(Exception e)
		{
			throw new HandleCustomException("400",e.getMessage());
		}
	}
	
	
	
}
