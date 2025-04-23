package com.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id_user;
	
	@Column(name="first_name")
	private String fname_user;
	
	@Column(name="last_name")
	private String lname_user;
	
	@Column(name="email")
	private String email_user;
	
	@Column(name="telephone")
	private String telephone_user;
	
	@Column(name="password")
	private String password_user;
	
	public User() {}

	public User(String fname_user, String lname_user, String email_user, String telephone_user, String password_user) {
		this.fname_user = fname_user;
		this.lname_user = lname_user;
		this.email_user = email_user;
		this.telephone_user = telephone_user;
		this.password_user = password_user;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getFname_user() {
		return fname_user;
	}

	public void setFname_user(String fname_user) {
		this.fname_user = fname_user;
	}

	public String getLname_user() {
		return lname_user;
	}

	public void setLname_user(String lname_user) {
		this.lname_user = lname_user;
	}

	public String getEmail_user() {
		return email_user;
	}

	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}

	public String getTelephone_user() {
		return telephone_user;
	}

	public void setTelephone_user(String telephone_user) {
		this.telephone_user = telephone_user;
	}

	public String getPassword_user() {
		return password_user;
	}

	public void setPassword_user(String password_user) {
		this.password_user = password_user;
	}

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", fname_user=" + fname_user + ", lname_user=" + lname_user
				+ ", email_user=" + email_user + ", telephone_user=" + telephone_user + ", password_user="
				+ password_user + "]";
	}
	
	
}
