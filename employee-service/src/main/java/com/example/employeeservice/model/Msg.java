package com.example.employeeservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Msg {

	@Id
	Integer id;
	String msg;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(Integer id , String msg) {
		this.id = id;
		this.msg = msg;
	}

	public Msg(String msg) {
		super();
		this.msg = msg;
	}
	
	public Msg() {
	}
	
	
}
