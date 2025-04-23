package com.hql.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="keyboard")
public class Keyboard {

	@Id
	
	@Column(name="id")
	private String id_k;
	
	@Column(name="name")
	private String name_k;
	
	@Column(name="brand")
	private String brand_k;
	
	@Column(name="price")
	private int price_k;
	
	public Keyboard() {}

	public Keyboard(String id_k, String name_k, String brand_k, int price_k) {
		this.id_k = id_k;
		this.name_k = name_k;
		this.brand_k = brand_k;
		this.price_k = price_k;
	}

	public String getId_k() {
		return id_k;
	}

	public void setId_k(String id_k) {
		this.id_k = id_k;
	}

	public String getName_k() {
		return name_k;
	}

	public void setName_k(String name_k) {
		this.name_k = name_k;
	}

	public String getBrand_k() {
		return brand_k;
	}

	public void setBrand_k(String brand_k) {
		this.brand_k = brand_k;
	}

	public int getPrice_k() {
		return price_k;
	}

	public void setPrice_k(int price_k) {
		this.price_k = price_k;
	}

	@Override
	public String toString() {
		return "Keyboard [id_k=" + id_k + ", name_k=" + name_k + ", brand_k=" + brand_k + ", price_k=" + price_k + "]";
	}
	
}
