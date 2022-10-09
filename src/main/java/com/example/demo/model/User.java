package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.enums.PersonStatus;

@Entity
@Table
public class User{
	@Id  
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String userName;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	private PersonStatus status;
	
	@Column
	private Date startDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PersonStatus getStatus() {
		return status;
	}

	public void setStatus(PersonStatus status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
