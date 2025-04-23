package com.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cartoon")
public class Cartoon {
	
	@Id
	@Column(name="id")
	private String c_id;
	
	@Column(name="bookname")
	private String c_name;
	
	@Column(name="author")
	private String c_author;
	
	@Column(name="publisher")
	private String c_publisher;
	
	@Column(name="price")
	private int c_price;
	
	public Cartoon() {}

	public Cartoon(String c_id, String c_name, String c_author, String c_publisher, int c_price) {
		this.c_id = c_id;
		this.c_name = c_name;
		this.c_author = c_author;
		this.c_publisher = c_publisher;
		this.c_price = c_price;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_author() {
		return c_author;
	}

	public void setC_author(String c_author) {
		this.c_author = c_author;
	}

	public String getC_publisher() {
		return c_publisher;
	}

	public void setC_publisher(String c_publisher) {
		this.c_publisher = c_publisher;
	}

	public int getC_price() {
		return c_price;
	}

	public void setC_price(int c_price) {
		this.c_price = c_price;
	}

	@Override
	public String toString() {
		return "Cartoon [c_id=" + c_id + ", c_name=" + c_name + ", c_author=" + c_author + ", c_publisher="
				+ c_publisher + ", c_price=" + c_price + "]";
	}
	
	
}
