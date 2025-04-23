package com.information.entity;

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
	
	@Column(name="id_member")
	private int id_member;
	
	@Column(name="first_name_member")
	private String firstname_member;
	
	@Column(name="last_name_member")
	private String lastname_member;
	
	@Column(name="email_member")
	private String email_member;
	
	@Column(name="gender_member")
	private String gender_member;
	
	@Column(name="age_member")
	private int age_member;
	
	@Column(name="telephone_number_member")
	private String telephone_member;
	
	@Column(name="password_member")
	private String password_member;

	public Member() {}
	
	public Member(String firstname_member, String lastname_member, String email_member, String gender_member,
			int age_member, String telephone_member, String password_member) {
		this.firstname_member = firstname_member;
		this.lastname_member = lastname_member;
		this.email_member = email_member;
		this.gender_member = gender_member;
		this.age_member = age_member;
		this.telephone_member = telephone_member;
		this.password_member = password_member;
	}

	public int getId_member() {
		return id_member;
	}

	public void setId_member(int id_member) {
		this.id_member = id_member;
	}

	public String getFirstname_member() {
		return firstname_member;
	}

	public void setFirstname_member(String firstname_member) {
		this.firstname_member = firstname_member;
	}

	public String getLastname_member() {
		return lastname_member;
	}

	public void setLastname_member(String lastname_member) {
		this.lastname_member = lastname_member;
	}

	public String getEmail_member() {
		return email_member;
	}

	public void setEmail_member(String email_member) {
		this.email_member = email_member;
	}

	public String getGender_member() {
		return gender_member;
	}

	public void setGender_member(String gender_member) {
		this.gender_member = gender_member;
	}

	public int getAge_member() {
		return age_member;
	}

	public void setAge_member(int age_member) {
		this.age_member = age_member;
	}

	public String getTelephone_member() {
		return telephone_member;
	}

	public void setTelephone_member(String telephone_member) {
		this.telephone_member = telephone_member;
	}

	public String getPassword_member() {
		return password_member;
	}

	public void setPassword_member(String password_member) {
		this.password_member = password_member;
	}

	@Override
	public String toString() {
		return "Member [id_member=" + id_member + ", firstname_member=" + firstname_member + ", lastname_member="
				+ lastname_member + ", email_member=" + email_member + ", gender_member=" + gender_member
				+ ", age_member=" + age_member + ", telephone_member=" + telephone_member + ", password_member="
				+ password_member + "]";
	}
	

	
}
