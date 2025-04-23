package com.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gaming_gear")
public class GamingGear {
	
	@Id
	@Column(name="id_gaminggear")
	private String id_game;
	
	@Column(name="name_gaminggear")
	private String name_game;
	
	@Column(name="brand_gaminggear")
	private String brand_game;
	
	@Column(name="type_gaminggear")
	private String type_game;
	
	@Column(name="price_gaminggear")
	private int price_game;
	
	public GamingGear() {}
	
	public GamingGear(String id_game, String name_game, String brand_game, String type_game, int price_game) {
		this.id_game = id_game;
		this.name_game = name_game;
		this.brand_game = brand_game;
		this.type_game = type_game;
		this.price_game = price_game;
	}

	public String getId_game() {
		return id_game;
	}

	public void setId_game(String id_game) {
		this.id_game = id_game;
	}

	public String getName_game() {
		return name_game;
	}

	public void setName_game(String name_game) {
		this.name_game = name_game;
	}

	public String getBrand_game() {
		return brand_game;
	}

	public void setBrand_game(String brand_game) {
		this.brand_game = brand_game;
	}

	public String getType_game() {
		return type_game;
	}

	public void setType_game(String type_game) {
		this.type_game = type_game;
	}

	public int getPrice_game() {
		return price_game;
	}

	public void setPrice_game(int price_game) {
		this.price_game = price_game;
	}

	@Override
	public String toString() {
		return "GamingGear [id_game=" + id_game + ", name_game=" + name_game + ", brand_game=" + brand_game
				+ ", type_game=" + type_game + ", price_game=" + price_game + "]";
	}
	
}
