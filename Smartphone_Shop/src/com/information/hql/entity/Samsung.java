package com.information.hql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="samsung")
public class Samsung {

	@Id
	
	@Column(name="id")
	private String id_s;
	
	@Column(name="name")
	private String name_s;
	
	@Column(name="brand")
	private String brand_s;
	
	@Column(name="size")
	private String size_s;
	
	@Column(name="price")
	private int price_s;
	
	public Samsung() {}

	public Samsung(String id_s, String name_s, String brand_s, String size_s, int price_s) {
		this.id_s = id_s;
		this.name_s = name_s;
		this.brand_s = brand_s;
		this.size_s = size_s;
		this.price_s = price_s;
	}

	public String getId_s() {
		return id_s;
	}

	public void setId_s(String id_s) {
		this.id_s = id_s;
	}

	public String getName_s() {
		return name_s;
	}

	public void setName_s(String name_s) {
		this.name_s = name_s;
	}

	public String getBrand_s() {
		return brand_s;
	}

	public void setBrand_s(String brand_s) {
		this.brand_s = brand_s;
	}

	public String getSize_s() {
		return size_s;
	}

	public void setSize_s(String size_s) {
		this.size_s = size_s;
	}

	public int getPrice_s() {
		return price_s;
	}

	public void setPrice_s(int price_s) {
		this.price_s = price_s;
	}

	@Override
	public String toString() {
		return "Samsung [id_s=" + id_s + ", name_s=" + name_s + ", brand_s=" + brand_s + ", size_s=" + size_s
				+ ", price_s=" + price_s + "]";
	}
	
}
