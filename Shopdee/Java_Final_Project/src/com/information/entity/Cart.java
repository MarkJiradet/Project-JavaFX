package com.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id_cart")
	private int id_cart;
	
	@Column(name="id_product")
	private String id_product;
	
	@Column(name="name_product")
	private String name_product;
	
	@Column(name="brand_product")
	private String brand_product;
	
	@Column(name="type_size_product")
	private String ts_product;
	
	@Column(name="price_product")
	private int price_product;
	
	public Cart() {}

	public Cart(String id_product, String name_product, String brand_product, String ts_product, int price_product) {
		this.id_product = id_product;
		this.name_product = name_product;
		this.brand_product = brand_product;
		this.ts_product = ts_product;
		this.price_product = price_product;
	}

	public int getId_cart() {
		return id_cart;
	}

	public void setId_cart(int id_cart) {
		this.id_cart = id_cart;
	}

	public String getId_product() {
		return id_product;
	}

	public void setId_product(String id_product) {
		this.id_product = id_product;
	}

	public String getName_product() {
		return name_product;
	}

	public void setName_product(String name_product) {
		this.name_product = name_product;
	}

	public String getBrand_product() {
		return brand_product;
	}

	public void setBrand_product(String brand_product) {
		this.brand_product = brand_product;
	}

	public String getTs_product() {
		return ts_product;
	}

	public void setTs_product(String ts_product) {
		this.ts_product = ts_product;
	}

	public int getPrice_product() {
		return price_product;
	}

	public void setPrice_product(int price_product) {
		this.price_product = price_product;
	}

	@Override
	public String toString() {
		return "Cart [id_cart=" + id_cart + ", id_product=" + id_product + ", name_product=" + name_product
				+ ", brand_product=" + brand_product + ", ts_product=" + ts_product + ", price_product=" + price_product
				+ "]";
	}
	
	
}
