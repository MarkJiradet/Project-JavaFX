package com.information.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="invoice")
public class Invoice {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id_invoice;
	
	@Column(name="price")
	private int price_invoice;
	
	public Invoice() {}

	public Invoice(int price_invoice) {
		this.price_invoice = price_invoice;
	}

	public int getId_invoice() {
		return id_invoice;
	}

	public void setId_invoice(int id_invoice) {
		this.id_invoice = id_invoice;
	}

	public int getPrice_invoice() {
		return price_invoice;
	}

	public void setPrice_invoice(int price_invoice) {
		this.price_invoice = price_invoice;
	}

	@Override
	public String toString() {
		return "Invoice [id_invoice=" + id_invoice + ", price_invoice=" + price_invoice + "]";
	}
	
}
