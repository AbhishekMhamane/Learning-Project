package com.example.employeeservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import jakarta.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.employeeservice.model.Organization;
import com.example.employeeservice.model.Department;

@Entity
public class Organization {
	
	@Id
	Integer orgId;
	
	String orgName;
	
	String orgOwner;
		
	String orgEmail;
	
	String orgMobileNo;

	@OneToMany(mappedBy = "org", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Employee> emp;

	@OneToMany(mappedBy = "org", cascade = CascadeType.ALL)
	List<Department> dept;

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

	public List<Employee> getEmp() {
		return emp;
	}

	public void setEmp(List<Employee> emp) {
		this.emp = emp;
	}

	public List<Department> getDept() {
		return dept;
	}

	public void setDept(List<Department> dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Organization [orgId=" + orgId + ", orgName=" + orgName + ", orgOwner=" + orgOwner + ", orgEmail="
				+ orgEmail + ", orgMobileNo=" + orgMobileNo + "]";
	}
	

}
