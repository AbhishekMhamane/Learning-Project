package com.example.employeeservice.model;

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
	Integer deptId;
	
	String deptName;
	
	String deptOwner;

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

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", deptOwner=" + deptOwner;
	}
	
}
