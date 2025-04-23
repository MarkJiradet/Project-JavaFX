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
	private int invoice_id;
	
	@Column(name="first_name")
	private String invoice_fname;
	
	@Column(name="last_name")
	private String invoice_lname;
	
	@Column(name="telephone")
	private String invoice_phone;
	
	@Column(name="email")
	private String invoice_email;
	
	@Column(name="total_price")
	private int invoice_total;

	public Invoice() {}
	
	public Invoice(String invoice_fname, String invoice_lname, String invoice_phone,
			String invoice_email, int invoice_total) {
		this.invoice_fname = invoice_fname;
		this.invoice_lname = invoice_lname;
		this.invoice_phone = invoice_phone;
		this.invoice_email = invoice_email;
		this.invoice_total = invoice_total;
	}

	public int getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}

	public String getInvoice_fname() {
		return invoice_fname;
	}

	public void setInvoice_fname(String invoice_fname) {
		this.invoice_fname = invoice_fname;
	}

	public String getInvoice_lname() {
		return invoice_lname;
	}

	public void setInvoice_lname(String invoice_lname) {
		this.invoice_lname = invoice_lname;
	}

	public String getInvoice_phone() {
		return invoice_phone;
	}

	public void setInvoice_phone(String invoice_phone) {
		this.invoice_phone = invoice_phone;
	}

	public String getInvoice_email() {
		return invoice_email;
	}

	public void setInvoice_email(String invoice_email) {
		this.invoice_email = invoice_email;
	}

	public int getInvoice_total() {
		return invoice_total;
	}

	public void setInvoice_total(int invoice_total) {
		this.invoice_total = invoice_total;
	}

	@Override
	public String toString() {
		return "Invoice [invoice_id=" + invoice_id + ", invoice_fname=" + invoice_fname + ", invoice_lname="
				+ invoice_lname + ", invoice_phone=" + invoice_phone + ", invoice_email=" + invoice_email
				+ ", invoice_total=" + invoice_total + "]";
	}
	
}
