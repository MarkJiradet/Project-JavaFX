package com.hql.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="headphone")
public class Headphone {

	@Id
	
	@Column(name="id")
	private String id_h;
	
	@Column(name="name")
	private String name_h;
	
	@Column(name="brand")
	private String brand_h;
	
	@Column(name="price")
	private int price_h;
	
	public Headphone() {}

	public Headphone(String id_h, String name_h, String brand_h, int price_h) {
		this.id_h = id_h;
		this.name_h = name_h;
		this.brand_h = brand_h;
		this.price_h = price_h;
	}

	public String getId_h() {
		return id_h;
	}

	public void setId_h(String id_h) {
		this.id_h = id_h;
	}

	public String getName_h() {
		return name_h;
	}

	public void setName_h(String name_h) {
		this.name_h = name_h;
	}

	public String getBrand_h() {
		return brand_h;
	}

	public void setBrand_h(String brand_h) {
		this.brand_h = brand_h;
	}

	public int getPrice_h() {
		return price_h;
	}

	public void setPrice_h(int price_h) {
		this.price_h = price_h;
	}

	@Override
	public String toString() {
		return "Headphone [id_h=" + id_h + ", name_h=" + name_h + ", brand_h=" + brand_h + ", price_h=" + price_h + "]";
	}
	
}
