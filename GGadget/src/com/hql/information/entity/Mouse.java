package com.hql.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mouse")
public class Mouse {
	
	@Id
	
	@Column(name="id")
	private String id_m;
	
	@Column(name="name")
	private String name_m;
	
	@Column(name="brand")
	private String brand_m;
	
	@Column(name="price")
	private int price_m;
	
	public Mouse() {}

	public Mouse(String id_m, String name_m, String brand_m, int price_m) {
		this.id_m = id_m;
		this.name_m = name_m;
		this.brand_m = brand_m;
		this.price_m = price_m;
	}

	public String getId_m() {
		return id_m;
	}

	public void setId_m(String id_m) {
		this.id_m = id_m;
	}

	public String getName_m() {
		return name_m;
	}

	public void setName_m(String name_m) {
		this.name_m = name_m;
	}

	public String getBrand_m() {
		return brand_m;
	}

	public void setBrand_m(String brand_m) {
		this.brand_m = brand_m;
	}

	public int getPrice_m() {
		return price_m;
	}

	public void setPrice_m(int price_m) {
		this.price_m = price_m;
	}

	@Override
	public String toString() {
		return "Mouse [id_m=" + id_m + ", name_m=" + name_m + ", brand_m=" + brand_m + ", price_m=" + price_m + "]";
	}
	
}
