package com.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id_admin")
	private int id_admin;
	
	@Column(name="first_name_admin")
	private String firstname_admin;
	
	@Column(name="last_name_admin")
	private String lastname_admin;
	
	@Column(name="email_admin")
	private String email_admin;
	
	@Column(name="gender_admin")
	private String gender_admin;
	
	@Column(name="age_admin")
	private int age_admin;
	
	@Column(name="telephone_admin")
	private String telephone_admin;
	
	@Column(name="password_admin")
	private String password_admin;
	
	public Admin() {}
	
	public Admin(String firstname_admin, String lastname_admin, String email_admin, String gender_admin, int age_admin,
			String telephone_admin,String password_admin) {
		this.firstname_admin = firstname_admin;
		this.lastname_admin = lastname_admin;
		this.email_admin = email_admin;
		this.gender_admin = gender_admin;
		this.age_admin = age_admin;
		this.telephone_admin = telephone_admin;
		this.password_admin = password_admin;
	}

	public int getId_admin() {
		return id_admin;
	}

	public void setId_admin(int id_admin) {
		this.id_admin = id_admin;
	}

	public String getFirstname_admin() {
		return firstname_admin;
	}

	public void setFirstname_admin(String firstname_admin) {
		this.firstname_admin = firstname_admin;
	}

	public String getLastname_admin() {
		return lastname_admin;
	}

	public void setLastname_admin(String lastname_admin) {
		this.lastname_admin = lastname_admin;
	}

	public String getEmail_admin() {
		return email_admin;
	}

	public void setEmail_admin(String email_admin) {
		this.email_admin = email_admin;
	}

	public String getGender_admin() {
		return gender_admin;
	}

	public void setGender_admin(String gender_admin) {
		this.gender_admin = gender_admin;
	}

	public int getAge_admin() {
		return age_admin;
	}

	public void setAge_admin(int age_admin) {
		this.age_admin = age_admin;
	}

	public String getTelephone_admin() {
		return telephone_admin;
	}

	public void setTelephone_admin(String telephone_admin) {
		this.telephone_admin = telephone_admin;
	}

	public String getPassword_admin() {
		return password_admin;
	}

	public void setPassword_admin(String password_admin) {
		this.password_admin = password_admin;
	}

	@Override
	public String toString() {
		return "Admin [id_admin=" + id_admin + ", firstname_admin=" + firstname_admin + ", lastname_admin="
				+ lastname_admin + ", email_admin=" + email_admin + ", gender_admin=" + gender_admin + ", age_admin="
				+ age_admin + ", telephone_admin=" + telephone_admin + ", password_admin=" + password_admin + "]";
	}

}
