package com.example.employeeservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Document {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer docId;
	
	String docName;
	
	String docUrl;
	
	@ManyToOne
	@JoinColumn(name="emp_id")
	Employee employee;

	public Document(Integer docId, String docName, String docUrl, Employee employee) {
		super();
		this.docId = docId;
		this.docName = docName;
		this.docUrl = docUrl;
		this.employee = employee;
	}
	public Document(String docName, String docUrl, Employee employee) {
		super();
		this.docName = docName;
		this.docUrl = docUrl;
		this.employee = employee;
	}
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocUrl() {
		return docUrl;
	}
	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "Document [docId=" + docId + ", docName=" + docName + ", docUrl=" + docUrl + ", employee=" + employee
				+ "]";
	}
	
	
	
}
