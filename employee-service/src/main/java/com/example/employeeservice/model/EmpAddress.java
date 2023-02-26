package com.example.employeeservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EmpAddress {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer addressId;
	
	String city;
	
	Integer pincode;
	
	String addressType;
	
	@ManyToOne
	@JoinColumn(name="emp_id")
	@JsonIgnore
	Employee employee;

	public EmpAddress(String city, Integer pincode,String addressType) {
		super();
		this.city = city;
		this.pincode = pincode;
		this.addressType = addressType;
	}
	
	
	public EmpAddress(Integer addressId, String city, Integer pincode,String addressType) {
		super();
		this.addressId = addressId;
		this.city = city;
		this.pincode = pincode;
		this.addressType=addressType;
	}
	

	public EmpAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	

	public String getAddressType() {
		return addressType;
	}


	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	@Override
	public String toString() {
		return "EmpAddress [addressId=" + addressId + ", city=" + city + ", pincode=" + pincode + ", addressType="
				+ addressType + ", employee=" + employee + "]";
	}

	
	
}
