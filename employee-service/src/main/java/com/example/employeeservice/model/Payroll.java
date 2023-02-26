package com.example.employeeservice.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Payroll {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer payrollId;
	
	@ManyToOne
	@JoinColumn(name="emp_id")
	Employee employee;
	
	@ManyToOne
	@JoinColumn(name="salaryid")
	SalaryDetails salaryDetails;

	Date date;
	
	Integer totalAmmount;

	public Payroll() {
		super();
	}

	public Payroll(Integer payrollId, Employee employee, SalaryDetails salaryDetails, Date date,
			Integer totalAmmount) {
		super();
		this.payrollId = payrollId;
		this.employee = employee;
		this.salaryDetails = salaryDetails;
		this.date = date;
		this.totalAmmount = totalAmmount;
	}
	
	public Payroll( Employee employee, SalaryDetails salaryDetails, Date date,
			Integer totalAmmount) {
		super();
		this.employee = employee;
		this.salaryDetails = salaryDetails;
		this.date = date;
		this.totalAmmount = totalAmmount;
	}

	public Integer getPayrollId() {
		return payrollId;
	}

	public void setPayrollId(Integer payrollId) {
		this.payrollId = payrollId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public SalaryDetails getSalaryDetails() {
		return salaryDetails;
	}

	public void setSalaryDetails(SalaryDetails salaryDetails) {
		this.salaryDetails = salaryDetails;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getTotalAmmount() {
		return totalAmmount;
	}

	public void setTotalAmmount(Integer totalAmmount) {
		this.totalAmmount = totalAmmount;
	}

	@Override
	public String toString() {
		return "Payroll [payrollId=" + payrollId + ", employee=" + employee + ", salaryDetails=" + salaryDetails
				+ ", date=" + date + ", totalAmmount=" + totalAmmount + "]";
	}
	
	
	
}
