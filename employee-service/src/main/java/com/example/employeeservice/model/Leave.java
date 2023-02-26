package com.example.employeeservice.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="leaves")
public class Leave {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer leaveId;
	
	Date date;
	
	String reason;
	
	@ManyToOne
	@JoinColumn(name="emp_id")
	Employee employee;

	public Leave(Integer leaveId, Date date, String reason) {
		super();
		this.leaveId = leaveId;
		this.date = date;
		this.reason = reason;
	}
	
	public Leave( Date date, String reason) {
		super();
		this.date = date;
		this.reason = reason;
	}
	
	public Leave() {
		super();
	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Leave [leaveId=" + leaveId + ", date=" + date + ", reason=" + reason + ", employee=" + employee + "]";
	}
	
	
}
