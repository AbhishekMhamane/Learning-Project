package com.example.organizationservice.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Department {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer deptId;
	
	String deptName;
	
	String deptOwner;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="org_id")
	Organization org;


	public Department(String deptName, String deptOwner, Organization org) {
		super();
		this.deptName = deptName;
		this.deptOwner = deptOwner;
		this.org = org;
	}
	public Department(Integer deptId, String deptName, String deptOwner, Organization org) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.deptOwner = deptOwner;
		this.org = org;
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
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", deptOwner=" + deptOwner + ", org=" + org
				+ "]";
	}
	
}
