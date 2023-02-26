package com.example.employeeservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SalaryDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer salaryId;
	
	Integer amount;
	
	Integer anual;
	
	Integer bonus;
	
	String bankName;
	
	String bankAccountNo;
	
	String bankIfsc;
	
	@OneToOne
	@JoinColumn(name="emp_id")
	Employee employee;
	
//	@OneToMany
//	@JsonIgnore
//	Payroll payroll;
	

	public SalaryDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalaryDetails(Integer salaryId, Integer amount, Integer anual, Integer bonus, String bankName,
			String bankAccountNo, String bankIfsc) {
		super();
		this.salaryId = salaryId;
		this.amount = amount;
		this.anual = anual;
		this.bonus = bonus;
		this.bankName = bankName;
		this.bankAccountNo = bankAccountNo;
		this.bankIfsc = bankIfsc;
	}
	
	public SalaryDetails(Integer amount, Integer anual, Integer bonus, String bankName,
			String bankAccountNo, String bankIfsc) {
		super();
		this.amount = amount;
		this.anual = anual;
		this.bonus = bonus;
		this.bankName = bankName;
		this.bankAccountNo = bankAccountNo;
		this.bankIfsc = bankIfsc;
	}

	public Integer getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(Integer salaryId) {
		this.salaryId = salaryId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getAnual() {
		return anual;
	}

	public void setAnual(Integer anual) {
		this.anual = anual;
	}

	public Integer getBonus() {
		return bonus;
	}

	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankIfsc() {
		return bankIfsc;
	}

	public void setBankIfsc(String bankIfsc) {
		this.bankIfsc = bankIfsc;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "SalaryDetails [salaryId=" + salaryId + ", amount=" + amount + ", anual=" + anual + ", bonus=" + bonus
				+ ", bankName=" + bankName + ", bankAccountNo=" + bankAccountNo + ", bankIfsc=" + bankIfsc
				+ ", employee=" + employee + "]";
	}
	
}
