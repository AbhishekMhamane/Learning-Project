package com.common.shareddtos.organization_service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrgOfficeLocation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer addressId;
	
	String city;
	
	String country;
	
	String pincode;
	
	//it is for which type of organization office is such as headquater,division or etc
	String typeOfOffice;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="org_id")
	Organization org;

	public OrgOfficeLocation(Integer addressId, String city, String country, String pincode,String typeOfOffice) {
		super();
		this.addressId = addressId;
		this.city = city;
		this.country = country;
		this.pincode = pincode;
		this.typeOfOffice = typeOfOffice;
	}

	public OrgOfficeLocation(String city, String country, String pincode, String typeOfOffice) {
		super();
		this.city = city;
		this.country = country;
		this.pincode = pincode;
		this.typeOfOffice = typeOfOffice;
	}

	public OrgOfficeLocation() {
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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getTypeOfOffice() {
		return typeOfOffice;
	}

	public void setTypeOfOffice(String typeOfOffice) {
		this.typeOfOffice = typeOfOffice;
	}

	@Override
	public String toString() {
		return "OrganizationAddress [addressId=" + addressId + ", city=" + city + ", country=" + country + ", pincode="
				+ pincode + ", typeOfOffice=" + typeOfOffice + ", org=" + org + "]";
	}


}
