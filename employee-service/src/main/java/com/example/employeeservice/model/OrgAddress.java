package com.example.employeeservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrgAddress {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer addressId;
	
	String city;
	
	String country;
	
	Integer pinCode;
	
	@ManyToOne
	@JoinColumn(name="org_id")
	Organization org;

	public OrgAddress(Integer addressId, String city, String country, Integer pinCode, Organization org) {
		super();
		this.addressId = addressId;
		this.city = city;
		this.country = country;
		this.pinCode = pinCode;
		this.org = org;
	}

	public OrgAddress(String city, String country, Integer pinCode, Organization org) {
		super();
		this.city = city;
		this.country = country;
		this.pinCode = pinCode;
		this.org = org;
	}

	public OrgAddress() {
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	@Override
	public String toString() {
		return "OrgAddress [addressId=" + addressId + ", city=" + city + ", country=" + country + ", pinCode=" + pinCode
				+ ", org=" + org + "]";
	}
	

}
