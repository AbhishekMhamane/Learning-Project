package com.example.employeeservice.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
