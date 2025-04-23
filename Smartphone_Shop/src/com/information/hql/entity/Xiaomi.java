package com.information.hql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="xiaomi")
public class Xiaomi {
	
	@Id
	
	@Column(name="id")
	private String id_x;
	
	@Column(name="name")
	private String name_x;
	
	@Column(name="brand")
	private String brand_x;
	
	@Column(name="size")
	private String size_x;
	
	@Column(name="price")
	private int price_x;
	
	public Xiaomi() {}

	public Xiaomi(String id_x, String name_x, String brand_x, String size_x, int price_x) {
		this.id_x = id_x;
		this.name_x = name_x;
		this.brand_x = brand_x;
		this.size_x = size_x;
		this.price_x = price_x;
	}

	public String getId_x() {
		return id_x;
	}

	public void setId_x(String id_x) {
		this.id_x = id_x;
	}

	public String getName_x() {
		return name_x;
	}

	public void setName_x(String name_x) {
		this.name_x = name_x;
	}

	public String getBrand_x() {
		return brand_x;
	}

	public void setBrand_x(String brand_x) {
		this.brand_x = brand_x;
	}

	public String getSize_x() {
		return size_x;
	}

	public void setSize_x(String size_x) {
		this.size_x = size_x;
	}

	public int getPrice_x() {
		return price_x;
	}

	public void setPrice_x(int price_x) {
		this.price_x = price_x;
	}

	@Override
	public String toString() {
		return "Xiaomi [id_x=" + id_x + ", name_x=" + name_x + ", brand_x=" + brand_x + ", size_x=" + size_x
				+ ", price_x=" + price_x + "]";
	}
	
}
