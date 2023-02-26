package com.example.employeeservice.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Qualification {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer qualificationId;
	
	String qualificationName;
	
	Date date;
	
	@ManyToOne
	@JoinColumn(name="emp_id")
	Employee employee;
	
	@OneToOne
	@JoinColumn(name="doc_id")
	Document document;

	public Qualification(Integer qualificationId, String qualificationName, Date date, Employee employee,
			Document document) {
		super();
		this.qualificationId = qualificationId;
		this.qualificationName = qualificationName;
		this.date = date;
		this.employee = employee;
		this.document = document;
	}

	public Qualification( String qualificationName, Date date, Employee employee,
			Document document) {
		super();
		this.qualificationName = qualificationName;
		this.date = date;
		this.employee = employee;
		this.document = document;
	}

	public Qualification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(Integer qualificationId) {
		this.qualificationId = qualificationId;
	}

	public String getQualificationName() {
		return qualificationName;
	}

	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	@Override
	public String toString() {
		return "Qualification [qualificationId=" + qualificationId + ", qualificationName=" + qualificationName
				+ ", date=" + date + ", employee=" + employee + ", document=" + document + "]";
	}
	
	
	
}
