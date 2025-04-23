package com.hql.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member")
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id_member;
	
	@Column(name="firstname")
	private String fname_member;
	
	@Column(name="lastname")
	private String lname_member;
	
	@Column(name="telephone")
	private String phone_member;
	
	@Column(name="email")
	private String email_member;
	
	@Column(name="password")
	private String password_member;
	
	public Member() {}

	public Member(String fname_member, String lname_member, String phone_member, String email_member,
			String password_member) {
		this.fname_member = fname_member;
		this.lname_member = lname_member;
		this.phone_member = phone_member;
		this.email_member = email_member;
		this.password_member = password_member;
	}

	public int getId_member() {
		return id_member;
	}

	public void setId_member(int id_member) {
		this.id_member = id_member;
	}

	public String getFname_member() {
		return fname_member;
	}

	public void setFname_member(String fname_member) {
		this.fname_member = fname_member;
	}

	public String getLname_member() {
		return lname_member;
	}

	public void setLname_member(String lname_member) {
		this.lname_member = lname_member;
	}

	public String getPhone_member() {
		return phone_member;
	}

	public void setPhone_member(String phone_member) {
		this.phone_member = phone_member;
	}

	public String getEmail_member() {
		return email_member;
	}

	public void setEmail_member(String email_member) {
		this.email_member = email_member;
	}

	public String getPassword_member() {
		return password_member;
	}

	public void setPassword_member(String password_member) {
		this.password_member = password_member;
	}

	@Override
	public String toString() {
		return "Member [id_member=" + id_member + ", fname_member=" + fname_member + ", lname_member=" + lname_member
				+ ", phone_member=" + phone_member + ", email_member=" + email_member + ", password_member="
				+ password_member + "]";
	}
	
}
