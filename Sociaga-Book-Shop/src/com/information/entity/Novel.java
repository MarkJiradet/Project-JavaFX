package com.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="novel")
public class Novel {

	@Id
	@Column(name="id")
	private String n_id;
	
	@Column(name="bookname")
	private String n_name;
	
	@Column(name="author")
	private String n_author;
	
	@Column(name="publisher")
	private String n_publisher;
	
	@Column(name="price")
	private int n_price;
	
	public Novel() {}

	public Novel(String n_id, String n_name, String n_author, String n_publisher, int n_price) {
		this.n_id = n_id;
		this.n_name = n_name;
		this.n_author = n_author;
		this.n_publisher = n_publisher;
		this.n_price = n_price;
	}

	public String getN_id() {
		return n_id;
	}

	public void setN_id(String n_id) {
		this.n_id = n_id;
	}

	public String getN_name() {
		return n_name;
	}

	public void setN_name(String n_name) {
		this.n_name = n_name;
	}

	public String getN_author() {
		return n_author;
	}

	public void setN_author(String n_author) {
		this.n_author = n_author;
	}

	public String getN_publisher() {
		return n_publisher;
	}

	public void setN_publisher(String n_publisher) {
		this.n_publisher = n_publisher;
	}

	public int getN_price() {
		return n_price;
	}

	public void setN_price(int n_price) {
		this.n_price = n_price;
	}

	@Override
	public String toString() {
		return "Novel [n_id=" + n_id + ", n_name=" + n_name + ", n_author=" + n_author + ", n_publisher=" + n_publisher
				+ ", n_price=" + n_price + "]";
	}
	
	
}
