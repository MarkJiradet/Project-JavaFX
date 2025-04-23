package com.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {

	@Id
	@Column(name="id")
	private String id_cart;
	
	@Column(name="bookname")
	private String name_cart;
	
	@Column(name="author")
	private String author_cart;
	
	@Column(name="publisher")
	private String publisher_cart;
	
	@Column(name="price")
	private int price_cart;
	
	public Cart() {}

	public Cart(String id_cart, String name_cart, String author_cart, String publisher_cart, int price_cart) {
		this.id_cart = id_cart;
		this.name_cart = name_cart;
		this.author_cart = author_cart;
		this.publisher_cart = publisher_cart;
		this.price_cart = price_cart;
	}

	public String getId_cart() {
		return id_cart;
	}

	public void setId_cart(String id_cart) {
		this.id_cart = id_cart;
	}

	public String getName_cart() {
		return name_cart;
	}

	public void setName_cart(String name_cart) {
		this.name_cart = name_cart;
	}

	public String getAuthor_cart() {
		return author_cart;
	}

	public void setAuthor_cart(String author_cart) {
		this.author_cart = author_cart;
	}

	public String getPublisher_cart() {
		return publisher_cart;
	}

	public void setPublisher_cart(String publisher_cart) {
		this.publisher_cart = publisher_cart;
	}

	public int getPrice_cart() {
		return price_cart;
	}

	public void setPrice_cart(int price_cart) {
		this.price_cart = price_cart;
	}

	@Override
	public String toString() {
		return "Cart [id_cart=" + id_cart + ", name_cart=" + name_cart + ", author_cart=" + author_cart
				+ ", publisher_cart=" + publisher_cart + ", price_cart=" + price_cart + "]";
	}
	
}
