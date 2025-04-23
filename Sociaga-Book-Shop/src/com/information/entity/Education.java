package com.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="education")
public class Education {

	@Id
	@Column(name="id")
	private String e_id;
	
	@Column(name="bookname")
	private String e_name;
	
	@Column(name="author")
	private String e_author;
	
	@Column(name="publisher")
	private String e_publisher;
	
	@Column(name="price")
	private int e_price;
	
	public Education() {}

	public Education(String e_id, String e_name, String e_author, String e_publisher, int e_price) {
		this.e_id = e_id;
		this.e_name = e_name;
		this.e_author = e_author;
		this.e_publisher = e_publisher;
		this.e_price = e_price;
	}

	public String getE_id() {
		return e_id;
	}

	public void setE_id(String e_id) {
		this.e_id = e_id;
	}

	public String getE_name() {
		return e_name;
	}

	public void setE_name(String e_name) {
		this.e_name = e_name;
	}

	public String getE_author() {
		return e_author;
	}

	public void setE_author(String e_author) {
		this.e_author = e_author;
	}

	public String getE_publisher() {
		return e_publisher;
	}

	public void setE_publisher(String e_publisher) {
		this.e_publisher = e_publisher;
	}

	public int getE_price() {
		return e_price;
	}

	public void setE_price(int e_price) {
		this.e_price = e_price;
	}

	@Override
	public String toString() {
		return "Education [e_id=" + e_id + ", e_name=" + e_name + ", e_author=" + e_author + ", e_publisher="
				+ e_publisher + ", e_price=" + e_price + "]";
	}
	
}
