package com.example.employeeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.employeeservice.model.Department;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.Organization;
import com.example.employeeservice.repository.EmployeeRepository;

import custom_exception.HandleCustomException;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

	final private EmployeeRepository empRepo;
	
	@Autowired
	private WebClient webClient;
	
	@Value("${organizationService.url}")
	String orgUrl;
	
	EmployeeService(EmployeeRepository empRepo)
	{
		this.empRepo = empRepo;
	}
	

	public Employee getEmployee(Integer orgId)
	{
        Employee emp = new Employee("abhishek","mhamane","abhi@gmail.com","12345");
		
        //Organization org = getOrganizationById(orgId);
                
        //System.out.println(org);
		//emp.setOrg(org);
		
		return emp;
	}
	
	public Organization getOrganizationById(Integer orgId)
	{
		return webClient.get()
                .uri(orgUrl+"/org/"+orgId)
                .retrieve()
                        .bodyToMono(Organization.class).share().block();
	}
	
	public Department getDepartmentById(Integer deptId)
	{
		return webClient.get()
                .uri(orgUrl+"/dept/"+deptId)
                .retrieve()
                        .bodyToMono(Department.class).share().block();
	}

	public Employee addEmployee(Integer orgId, Integer deptId,Employee emp) {
		
		try
		{
			Organization org = getOrganizationById(orgId);
			Department dept = getDepartmentById(deptId);
			
			if(org!=null)
			{
				if(dept!=null)
				{
				      if(!emp.getFirstName().isBlank() && !emp.getFirstName().isEmpty())	
				      {
				    	  if(!emp.getLastName().isBlank() && !emp.getLastName().isEmpty())
				    	  {
				    		  if(!emp.getEmail().isBlank() && !emp.getEmail().isEmpty())
				    		  {
				    			  if(!emp.getMobileNo1().isBlank() && !emp.getMobileNo1().isEmpty())
				    			  {
				    				  emp.setDept(dept);
				    				  emp.setOrg(org);
				    				  
				    				  return empRepo.save(emp);
				    			  }
				    			  else
				  				  {
				  					throw new HandleCustomException("400","Enter valid mobile");
				  				  }
				    		  }
				    		  else
							  {
								throw new HandleCustomException("400","Enter valid email");
							  }
				    	  }
				    	  else
							{
								throw new HandleCustomException("400","Enter valid last name");
							}
				      }
				      else
						{
							throw new HandleCustomException("400","Enter valid first name");
						}
				}
				else
				{
					throw new HandleCustomException("400","Department not present with given id");
				}
			}
			else
			{
				throw new HandleCustomException("400","Organization not present with given id");
			}
		}
		catch(HandleCustomException e)
		{
			throw new HandleCustomException(e.getErrorCode(),e.getErrorMessage());
		}
		catch(Exception e)
		{
			throw new HandleCustomException("400",e.getMessage());
		}

	}
}
