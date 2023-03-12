package com.example.employeeservice.model;

import com.example.employeeservice.model.Organization;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Department {

	@Id
	Integer deptId;
	
	String deptName;
	
	String deptOwner;

	@ManyToOne
	@JoinColumn(name="org_id")
	@JsonIgnore
	Organization org;

	public Department(String deptName, String deptOwner) {
		super();
		this.deptName = deptName;
		this.deptOwner = deptOwner;
	}
	public Department(Integer deptId, String deptName, String deptOwner) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.deptOwner = deptOwner;
	}
	public Department() {
		super();
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptOwner() {
		return deptOwner;
	}
	public void setDeptOwner(String deptOwner) {
		this.deptOwner = deptOwner;
	}

	public Organization getOrg() {
		return org;
	}
	public void setOrg(Organization org) {
		this.org = org;
	}
	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", deptOwner=" + deptOwner;
	}
	
}
