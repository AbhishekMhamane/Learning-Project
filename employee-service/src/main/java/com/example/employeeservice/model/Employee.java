package com.example.employeeservice.model;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.example.employeeservice.model.Organization;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.employeeservice.model.Department;

@Entity
@Table(name="Employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer empId;
	
	String firstName;
	
	String lastName;
	
	String email;
	
	String mobileNo1;
	
	String mobileNo2;	

	@ManyToOne
	@JoinColumn(name="org_id")
	Organization org;
	
	@ManyToOne
	@JoinColumn(name="dept_id")
	Department dept;

	@OneToMany(cascade=CascadeType.ALL,mappedBy="employee")
	List<EmpAddress> empAddress = new ArrayList<>();

	@OneToOne(cascade=CascadeType.ALL,mappedBy="employee",fetch=FetchType.LAZY)
	SalaryDetails salaryDetails;	
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="employee",fetch=FetchType.LAZY)
	@JsonIgnore
	List<Payroll> payrollList = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="employee",fetch=FetchType.LAZY)
	@JsonIgnore
	List<Leave> leaveList = new ArrayList<>();
	


	public Employee() {
		super();
	}


	public Employee(Integer empId, String firstName, String lastName, String email, String mobileNo1, String mobileNo2) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNo1 = mobileNo1;
		this.mobileNo2 = mobileNo2;
	}
	
	public Employee(String firstName, String lastName, String email, String mobileNo1) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNo1 = mobileNo1;
	}


	public Integer getEmpId() {
		return empId;
	}


	public void setEmpId(Integer empId) {
		this.empId = empId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobileNo1() {
		return mobileNo1;
	}


	public void setMobileNo1(String mobileNo1) {
		this.mobileNo1 = mobileNo1;
	}


	public String getMobileNo2() {
		return mobileNo2;
	}


	public void setMobileNo2(String mobileNo2) {
		this.mobileNo2 = mobileNo2;
	}
    
	public Organization getOrg() {
		return org;
	}


	public void setOrg(Organization org) {
		this.org = org;
	}


	public Department getDept() {
		return dept;
	}


	public void setDept(Department dept) {
		this.dept = dept;
	}

	public List<EmpAddress> getEmpAddress() {
		return empAddress;
	}


	public void setEmpAddress(List<EmpAddress> empAddress) {
		this.empAddress = empAddress;
	}


	public SalaryDetails getSalaryDetails() {
		return salaryDetails;
	}


	public void setSalaryDetails(SalaryDetails salaryDetails) {
		this.salaryDetails = salaryDetails;
	}
	

	public List<Payroll> getPayrollList() {
		return payrollList;
	}


	public void setPayrollList(List<Payroll> payrollList) {
		this.payrollList = payrollList;
	}


	public List<Leave> getLeaveList() {
		return leaveList;
	}


	public void setLeaveList(List<Leave> leaveList) {
		this.leaveList = leaveList;
	}


	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", mobileNo1=" + mobileNo1 + ", mobileNo2=" + mobileNo2 + ", org=" + org + ", dept=" + dept
				+ ", empAddress=" + empAddress + ", salaryDetails=" + salaryDetails + ", payrollList=" + payrollList
				+ ", leaveList=" + leaveList + "]";
	}	
		
}

