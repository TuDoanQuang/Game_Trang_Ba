package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.demo.enums.PersonStatus;

@Entity
@Table(name = "User")
public class User{
	@Id  
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "userName")
	@NotBlank(message = "Name is mandatory")
	@Size(min = 5, max = 30, message = "Size must be between 5 and 30")
	private String userName;
	
	@Column(name = "email")
	@NotBlank(message = "Email is mandatory")
	@Email
	private String email;
	
	@Column(name = "password")
	@NotBlank(message = "Password is mandatory")
	@Size(min = 2)
	private String password;
	
	@Column(name = "status")
	private PersonStatus status;
	
	@Column(name = "startDate")
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
