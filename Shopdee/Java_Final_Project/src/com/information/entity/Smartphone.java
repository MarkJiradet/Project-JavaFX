package com.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="smartphone")
public class Smartphone {
	
	@Id
	@Column(name="id_phone")
	private String id_phone;
	
	@Column(name="name_phone")
	private String name_phone;
	
	@Column(name="brand_phone")
	private String brand_phone;
	
	@Column(name="size_phone")
	private String size_phone;
	
	@Column(name="price_phone")
	private int price_phone;
	
	public Smartphone() {}
	
	public Smartphone(String id_phone, String name_phone, String brand_phone, String size_phone, int price_phone) {
		this.id_phone = id_phone;
		this.name_phone = name_phone;
		this.brand_phone = brand_phone;
		this.size_phone = size_phone;
		this.price_phone = price_phone;
	}

	public String getId_phone() {
		return id_phone;
	}

	public void setId_phone(String id_phone) {
		this.id_phone = id_phone;
	}

	public String getName_phone() {
		return name_phone;
	}

	public void setName_phone(String name_phone) {
		this.name_phone = name_phone;
	}

	public String getBrand_phone() {
		return brand_phone;
	}

	public void setBrand_phone(String brand_phone) {
		this.brand_phone = brand_phone;
	}

	public String getSize_phone() {
		return size_phone;
	}

	public void setSize_phone(String size_phone) {
		this.size_phone = size_phone;
	}

	public int getPrice_phone() {
		return price_phone;
	}

	public void setPrice_phone(int price_phone) {
		this.price_phone = price_phone;
	}

	@Override
	public String toString() {
		return "Smartphone [id_phone=" + id_phone + ", name_phone=" + name_phone + ", brand_phone=" + brand_phone
				+ ", size_phone=" + size_phone + ", price_phone=" + price_phone + "]";
	}
	
}
