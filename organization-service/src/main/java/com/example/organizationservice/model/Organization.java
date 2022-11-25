package com.example.organizationservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Organization {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer orgId;
	
	String orgName;
	
	String orgOwner;
		
	String orgEmail;
	
	String orgMobileNo;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="org")
	List<OrgOfficeLocation> orgOfficeLocation = new ArrayList<OrgOfficeLocation>();
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="org")
	List<Department> deptList = new ArrayList<Department>();
	
	
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
	

	public List<OrgOfficeLocation> getOrgOfficeLocation() {
		return orgOfficeLocation;
	}

	public void setOrgOfficeLocation(List<OrgOfficeLocation> orgOfficeLocation) {
		this.orgOfficeLocation = orgOfficeLocation;
	}

	public List<Department> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}

	@Override
	public String toString() {
		return "Organization [orgId=" + orgId + ", orgName=" + orgName + ", orgOwner=" + orgOwner + ", orgEmail="
				+ orgEmail + ", orgMobileNo=" + orgMobileNo + "]";
	}
	

}
