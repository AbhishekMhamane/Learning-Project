package com.example.organizationservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.organizationservice.custom_exceptions.HandleCustomExceptions;
import com.example.organizationservice.model.Department;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.repository.DepartmentRepository;
import com.example.organizationservice.repository.OrganizationRepository;

@Service
public class DepartmentService {

	private final DepartmentRepository deptRepo;
	private final OrganizationRepository orgRepo;
	
	@Autowired
	DepartmentService(DepartmentRepository deptRepo,OrganizationRepository orgRepo)
	{
		this.deptRepo = deptRepo;
		this.orgRepo = orgRepo;
	}

	public Department getDepartmentsByDepartmentId(Integer deptId) {
		try 
		{
			boolean isDeptPresent = deptRepo.existsById(deptId);
			
			if(isDeptPresent)
			{
			    Optional<Department> deptRecord = deptRepo.findById(deptId);
			    
			    return deptRecord.get();
			}
			else
			{
				throw new HandleCustomExceptions("400","Department not preset by given id");
			}
			
	    }
		catch(HandleCustomExceptions e)
		{
			throw new HandleCustomExceptions(e.getErrorCode(),e.getErrorMessage());
		}
		catch(Exception e)
		{
			throw new HandleCustomExceptions("400",e.getMessage());
		}
	}

	public Department createDepartmentsByOrgId(Integer orgId, Department dept) {
		try 
		{
			Optional<Organization> orgOptional = orgRepo.findById(orgId);
			
			if(orgOptional.isPresent())
			{
				Organization orgRecord = orgOptional.get();
			
				//System.out.println(deptRepo.findByDepartmentByOrgIdAndDeptName(orgId,dept.getDeptName()));
				
                Optional<Department> isDeptPresent = deptRepo.findByDepartmentByOrgIdAndDeptName(orgId,dept.getDeptName());
				
				if(!isDeptPresent.isPresent())
				{
					dept.setOrg(orgRecord);
					return deptRepo.save(dept);	
				}
				else
				{
					throw new HandleCustomExceptions("400","Department present with same name in given organization");
				}
			}
			else
			{
				throw new HandleCustomExceptions("400","Organization not preset by given id");
			}
			
	    }
		catch(HandleCustomExceptions e)
		{
			throw new HandleCustomExceptions(e.getErrorCode(),e.getErrorMessage());
		}
		catch(Exception e)
		{
			throw new HandleCustomExceptions("400",e.getMessage());
		}
	}

	public Department updateDepartmentsByDepartmentId(Integer deptId,Department updateDept) {
		
		try 
		{
			Optional<Department> deptOptional = deptRepo.findById(deptId);
			
			//System.out.println(updateDept);
			if(deptOptional.isPresent())
			{
				Department deptRecord = deptOptional.get();
				
				if(updateDept.getDeptName()!=null && !updateDept.getDeptName().equals(deptRecord.getDeptName()))
				{
					deptRecord.setDeptName(updateDept.getDeptName());
				}
				
				if(updateDept.getDeptOwner()!=null && !updateDept.getDeptOwner().equals(deptRecord.getDeptOwner()))
				{
					deptRecord.setDeptOwner(updateDept.getDeptOwner());
				}
				
				return deptRepo.save(deptRecord);
				
			}
			else
			{
				throw new HandleCustomExceptions("400","Department not present with given id");
			}
	    }
		catch(HandleCustomExceptions e)
		{
			throw new HandleCustomExceptions(e.getErrorCode(),e.getErrorMessage());
		}
		catch(Exception e)
		{
			throw new HandleCustomExceptions("400",e.getMessage());
		}
	}

	public void deleteDepartmentsByDepartmentId(Integer deptId) {
		
		try 
		{
			boolean isDeptPresent = deptRepo.existsById(deptId);
			
			if(isDeptPresent)
			{
				deptRepo.deleteById(deptId);
			}
			else
			{
				throw new HandleCustomExceptions("400","Department not present with given id");
			}
	    }
		catch(HandleCustomExceptions e)
		{
			throw new HandleCustomExceptions(e.getErrorCode(),e.getErrorMessage());
		}
		catch(Exception e)
		{
			throw new HandleCustomExceptions("400",e.getMessage());
		}
	}
	
}
	
