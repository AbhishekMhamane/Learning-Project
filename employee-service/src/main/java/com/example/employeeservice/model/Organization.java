package com.example.employeeservice.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Organization {
	
	@Id
	Integer orgId;
	
	String orgName;
	
	String orgOwner;
		
	String orgEmail;
	
	String orgMobileNo;

	
	public Organization(Integer orgId, String orgName, String orgOwner, String orgEmail, String orgMobileNo) {
		super();
		this.orgId = orgId;
		this.orgName = orgName;
		this.orgOwner = orgOwner;
		this.orgEmail = orgEmail;
		this.orgMobileNo = orgMobileNo;
	}
	
	public Organization(String orgName, String orgOwner, String orgEmail, String orgMobileNo) {
		super();
		this.orgName = orgName;
		this.orgOwner = orgOwner;
		this.orgEmail = orgEmail;
		this.orgMobileNo = orgMobileNo;
	}

	public Organization() {
		super();
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgOwner() {
		return orgOwner;
	}

	public void setOrgOwner(String orgOwner) {
		this.orgOwner = orgOwner;
	}

	public String getOrgEmail() {
		return orgEmail;
	}

	public void setOrgEmail(String orgEmail) {
		this.orgEmail = orgEmail;
	}

	public String getOrgMobileNo() {
		return orgMobileNo;
	}

	public void setOrgMobileNo(String orgMobileNo) {
		this.orgMobileNo = orgMobileNo;
	}

	@Override
	public String toString() {
		return "Organization [orgId=" + orgId + ", orgName=" + orgName + ", orgOwner=" + orgOwner + ", orgEmail="
				+ orgEmail + ", orgMobileNo=" + orgMobileNo + "]";
	}
	

}
