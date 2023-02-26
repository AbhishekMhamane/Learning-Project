package com.example.organizationservice.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.organizationservice.custom_exceptions.HandleCustomExceptions;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrgOfficeLocation;
import com.example.organizationservice.repository.OrgOfficeLocationRepository;
import com.example.organizationservice.repository.OrganizationRepository;
import com.example.organizationservice.kafka.OrganizationEventProducer;

@Service
public class OrganizationService {

	private static final Logger log = LoggerFactory.getLogger(OrganizationService.class);

	private final OrganizationRepository orgRepository;
	private final OrgOfficeLocationRepository orgAddRepository;
	
	@Autowired
	private OrganizationEventProducer producer;

	@Autowired
	OrganizationService(OrganizationRepository orgRepository,OrgOfficeLocationRepository orgAddRepository)
	{
		this.orgRepository = orgRepository;
		this.orgAddRepository = orgAddRepository;
	}
	
	public List<Organization> getAllOrganization()
	{
		return orgRepository.findAll();
	}
	
	@Transactional
	public Organization addNewOrganization(Organization org)
	{
		try
		{
			if(!org.getOrgName().isEmpty() && !org.getOrgEmail().isEmpty() && !org.getOrgMobileNo().isEmpty() 
					&& !org.getOrgOwner().isEmpty())
			{
				Optional<Organization> orgRecordByName = orgRepository.findByOrgName(org.getOrgName());
				
				if(!orgRecordByName.isPresent())
				{
					Optional<Organization> orgRecordByEmail = orgRepository.findByOrgEmail(org.getOrgEmail());
					
					if(!orgRecordByEmail.isPresent())
					{
						Optional<Organization> orgRecordByMobile = orgRepository.findByOrgMobileNo(org.getOrgMobileNo());
						
						if(!orgRecordByMobile.isPresent())
						{
							Organization newOrg = orgRepository.save(org);
							producer.sendOrganizationEvent(newOrg, false);
							return newOrg;
						}
						else
						{
							throw new HandleCustomExceptions("404","Organization already present with same mobile number");
						}
					}
					else
					{
						throw new HandleCustomExceptions("404","Organization already present with same email id");
					}
					
				}
				else
				{
					throw new HandleCustomExceptions("400","Organization present with same name.");
				}

			}
			else
			{
				throw new HandleCustomExceptions("400","Please enter valid organization details");
			}
						
		}
		catch(HandleCustomExceptions e)
		{
			throw new HandleCustomExceptions(e.getErrorCode(),e.getErrorMessage());
		}
		catch(Exception e)
		{
			throw new HandleCustomExceptions(e.getMessage());
		}
		
	}

	public Organization getOrganizationById(Integer orgId) {
		
		try
		{
			Optional<Organization> orgRecord = orgRepository.findById(orgId);
			
			if(orgRecord.isPresent())
			{
				return orgRecord.get();
			}
			else
			{
				throw new HandleCustomExceptions("404","Organization not present by given id");
			}
		}
		catch(HandleCustomExceptions e)
		{
			throw new HandleCustomExceptions(e.getErrorCode(),e.getErrorMessage());
		}
		catch(Exception e)
		{
			throw new HandleCustomExceptions("404",e.getMessage());
		}
	}

	public void deleteOrganizationById(Integer orgId) 
	{
		try
		{
			//boolean isOrgPresent = orgRepository.existsById(orgId);
			Optional<Organization> optionalOrg = orgRepository.findById(orgId);

			if(optionalOrg.isPresent())
			{
				Organization org = optionalOrg.get();
				orgRepository.deleteById(orgId);
				producer.sendOrganizationEvent(org,true);
			}
			else
			{
				throw new HandleCustomExceptions("404","Organization with given id not present");
			}
		}
		catch(HandleCustomExceptions e)
		{
			throw new HandleCustomExceptions(e);
		}
		catch(Exception e)
		{
			throw new HandleCustomExceptions("404",e.getMessage());
		}
	}

	public Organization updateOrganizationById(Integer orgId,Organization org) 
	{
		try
		{
			Optional<Organization> orgOptionalRecord = orgRepository.findById(orgId);
			
			if(orgOptionalRecord.isPresent())
			{
				Organization orgRecord = orgOptionalRecord.get();
				
				if(org.getOrgName()!=null && !org.getOrgName().equals(orgRecord.getOrgName()))
				{
					orgRecord.setOrgName(org.getOrgName());
				}
				else if(org.getOrgEmail()!=null && !org.getOrgEmail().equals(orgRecord.getOrgEmail()))
				{
					orgRecord.setOrgEmail(org.getOrgEmail());
				}
				else if(org.getOrgMobileNo()!=null && !org.getOrgMobileNo().equals(orgRecord.getOrgMobileNo()))
				{
					orgRecord.setOrgMobileNo(org.getOrgMobileNo());
				}
				else if(org.getOrgOwner()!=null && !org.getOrgOwner().equals(orgRecord.getOrgOwner()))
				{
					orgRecord.setOrgOwner(org.getOrgOwner());
				}
				else
				{
					throw new HandleCustomExceptions("404","Please enter valid attributes of organization");
				}
				
				Organization updatedOrg = orgRepository.save(orgRecord);
				producer.sendOrganizationEvent(updatedOrg,false);
				return updatedOrg;
					
			}
			else
			{
				throw new HandleCustomExceptions("400","Organization not present by given id");
			}
		}
		catch(HandleCustomExceptions e)
		{
			throw new HandleCustomExceptions(e);
		}
		catch(Exception e)
		{
			throw new HandleCustomExceptions("400",e.getMessage());
		}
		
	}

	public Organization addNewOrgOfficeLocation(Integer orgId,OrgOfficeLocation orgAddress) {
		
		try
		{
		    boolean isOrgPresent = orgRepository.existsById(orgId);
		    
		    if(isOrgPresent)
		    {
		    	
		        Optional<OrgOfficeLocation> orgAddressRecord = orgAddRepository.findByPincode(orgAddress.getPincode());
		    	
		        if(!orgAddressRecord.isPresent())
		    	{
		    		if(!orgAddress.getCity().isEmpty() && !orgAddress.getCountry().isEmpty() && !orgAddress.getPincode().isEmpty()
		    				&& !orgAddress.getTypeOfOffice().isEmpty())
		    		{
		    			Optional<Organization> orgOptionalRecord = orgRepository.findById(orgId);
		    			Organization orgRecord = orgOptionalRecord.get();
		    			orgAddress.setOrg(orgRecord);
		    			orgRecord.getOrgOfficeLocation().add(orgAddress);
		    			//System.out.println(orgRecord);
		    			return orgRepository.save(orgRecord);
		    		}
		    		else
		    		{
		    			throw new HandleCustomExceptions("404","Please give valid inputs");
		    		}
		    	}
		    	else
		    	{
		    		throw new HandleCustomExceptions("404","Address already added with given pincode");
		    	}
		    }
		    else
		    {
		    	throw new HandleCustomExceptions("404","Given organization id is not present");
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

	public OrgOfficeLocation updateOrgOfficeLocationById(Integer addressId,OrgOfficeLocation orgAddress) {
		try
		{

		        Optional<OrgOfficeLocation> orgAddressRecord = orgAddRepository.findById(addressId);
		    	
		        if(orgAddressRecord.isPresent())
		    	{
		        	OrgOfficeLocation orgAddRecord = orgAddressRecord.get();
		        	
		    		if(orgAddress.getCity()!=null && !orgAddress.getCity().isEmpty() && !orgAddress.getCity().equals(orgAddRecord.getCity()))
		    		{
		    			orgAddRecord.setCity(orgAddress.getCity());
		    		}
		    		
		    		if(orgAddress.getCountry()!=null && !orgAddress.getCountry().isEmpty() && !orgAddress.getCountry().equals(orgAddRecord.getCountry()))
		    		{
		    			orgAddRecord.setCountry(orgAddress.getCountry());
		    		}
		    		
		    		if(orgAddress.getPincode()!=null && !orgAddress.getPincode().isEmpty() && !orgAddress.getPincode().equals(orgAddRecord.getPincode()))
		    		{
		    			orgAddRecord.setPincode(orgAddress.getPincode());
		    		}
		    		
		    		if(orgAddress.getTypeOfOffice()!=null && !orgAddress.getTypeOfOffice().isEmpty() && !orgAddress.getTypeOfOffice().equals(orgAddRecord.getTypeOfOffice()))
		    		{
		    			orgAddRecord.setTypeOfOffice(orgAddress.getTypeOfOffice());
		    		}
		   
		    		return orgAddRepository.save(orgAddRecord);
		    		//orgRecord.getOrganizationAddress().add(orgAddRecord);
		    		
		    	    //return orgRepository.save(orgRecord);
		    	}
		    	else
		    	{
		    		throw new HandleCustomExceptions("404","Given address id is not present");
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

	public void deleteOrgOfficeLocationById(Integer addressId) {
		
		try
		{
			boolean isOfficeLocationPresent = orgAddRepository.existsById(addressId);
			
			if(isOfficeLocationPresent)
			{
				orgAddRepository.deleteById(addressId);
			}
			else
			{
				throw new HandleCustomExceptions("400","Given address id not present");
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
