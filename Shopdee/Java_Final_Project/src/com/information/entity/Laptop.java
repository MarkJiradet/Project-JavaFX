package com.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="laptop")
public class Laptop {
	
	@Id
	@Column(name="id_laptop")
	private String id_laptop;
	
	@Column(name="name_laptop")
	private String name_laptop;
	
	@Column(name="brand_laptop")
	private String brand_laptop;
	
	@Column(name="size_laptop")
	private String size_laptop;
	
	@Column(name="price_laptop")
	private int price_laptop;

	public Laptop() {}

	public Laptop(String id_laptop, String name_laptop, String brand_laptop, String size_laptop, int price_laptop) {
		this.id_laptop = id_laptop;
		this.name_laptop = name_laptop;
		this.brand_laptop = brand_laptop;
		this.size_laptop = size_laptop;
		this.price_laptop = price_laptop;
	}

	public String getId_laptop() {
		return id_laptop;
	}

	public void setId_laptop(String id_laptop) {
		this.id_laptop = id_laptop;
	}

	public String getName_laptop() {
		return name_laptop;
	}

	public void setName_laptop(String name_laptop) {
		this.name_laptop = name_laptop;
	}

	public String getBrand_laptop() {
		return brand_laptop;
	}

	public void setBrand_laptop(String brand_laptop) {
		this.brand_laptop = brand_laptop;
	}

	public String getSize_laptop() {
		return size_laptop;
	}

	public void setSize_laptop(String size_laptop) {
		this.size_laptop = size_laptop;
	}

	public int getPrice_laptop() {
		return price_laptop;
	}

	public void setPrice_laptop(int price_laptop) {
		this.price_laptop = price_laptop;
	}

	@Override
	public String toString() {
		return "Laptop [id_laptop=" + id_laptop + ", name_laptop=" + name_laptop + ", brand_laptop=" + brand_laptop
				+ ", size_laptop=" + size_laptop + ", price_laptop=" + price_laptop + "]";
	}
	
}
