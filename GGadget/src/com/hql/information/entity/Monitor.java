package com.hql.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="monitor")
public class Monitor {

	@Id
	
	@Column(name="id")
	private String id_mo;
	
	@Column(name="name")
	private String name_mo;
	
	@Column(name="brand")
	private String brand_mo;
	
	@Column(name="price")
	private int price_mo;
	
	public Monitor() {}

	public Monitor(String id_mo, String name_mo, String brand_mo, int price_mo) {
		this.id_mo = id_mo;
		this.name_mo = name_mo;
		this.brand_mo = brand_mo;
		this.price_mo = price_mo;
	}

	public String getId_mo() {
		return id_mo;
	}

	public void setId_mo(String id_mo) {
		this.id_mo = id_mo;
	}

	public String getName_mo() {
		return name_mo;
	}

	public void setName_mo(String name_mo) {
		this.name_mo = name_mo;
	}

	public String getBrand_mo() {
		return brand_mo;
	}

	public void setBrand_mo(String brand_mo) {
		this.brand_mo = brand_mo;
	}

	public int getPrice_mo() {
		return price_mo;
	}

	public void setPrice_mo(int price_mo) {
		this.price_mo = price_mo;
	}

	@Override
	public String toString() {
		return "Monitor [id_mo=" + id_mo + ", name_mo=" + name_mo + ", brand_mo=" + brand_mo + ", price_mo=" + price_mo
				+ "]";
	}
	
}
