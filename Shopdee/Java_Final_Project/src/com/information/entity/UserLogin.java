package com.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_login")
public class UserLogin {
	
	@Id
	
	@Column(name="first_name")
	private String user_fname;
	
	@Column(name="last_name")
	private String user_lname;
	
	@Column(name="email")
	private String user_email;
	
	@Column(name="telephone")
	private String user_telephone;
	
	public UserLogin() {}

	public UserLogin(String user_fname, String user_lname, String user_email, String user_telephone) {
		this.user_fname = user_fname;
		this.user_lname = user_lname;
		this.user_email = user_email;
		this.user_telephone = user_telephone;
	}

	public String getUser_fname() {
		return user_fname;
	}

	public void setUser_fname(String user_fname) {
		this.user_fname = user_fname;
	}

	public String getUser_lname() {
		return user_lname;
	}

	public void setUser_lname(String user_lname) {
		this.user_lname = user_lname;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_telephone() {
		return user_telephone;
	}

	public void setUser_telephone(String user_telephone) {
		this.user_telephone = user_telephone;
	}

	@Override
	public String toString() {
		return "UserLogin [user_fname=" + user_fname + ", user_lname=" + user_lname + ", user_email=" + user_email
				+ ", user_telephone=" + user_telephone + "]";
	}

	
}
