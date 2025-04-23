package com.hql.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="flashdrive")
public class FlashDrive {
	
	@Id
	
	@Column(name="id")
	private String id_f;
	
	@Column(name="name")
	private String name_f;

	@Column(name="brand")
	private String brand_f;

	@Column(name="price")
	private int price_f;
	
	public FlashDrive() {}

	public FlashDrive(String id_f, String name_f, String brand_f, int price_f) {
		this.id_f = id_f;
		this.name_f = name_f;
		this.brand_f = brand_f;
		this.price_f = price_f;
	}

	public String getId_f() {
		return id_f;
	}

	public void setId_f(String id_f) {
		this.id_f = id_f;
	}

	public String getName_f() {
		return name_f;
	}

	public void setName_f(String name_f) {
		this.name_f = name_f;
	}

	public String getBrand_f() {
		return brand_f;
	}

	public void setBrand_f(String brand_f) {
		this.brand_f = brand_f;
	}

	public int getPrice_f() {
		return price_f;
	}

	public void setPrice_f(int price_f) {
		this.price_f = price_f;
	}

	@Override
	public String toString() {
		return "FlashDrive [id_f=" + id_f + ", name_f=" + name_f + ", brand_f=" + brand_f + ", price_f=" + price_f
				+ "]";
	}
	
}
