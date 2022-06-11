package com.bank.analyzer.model;

import java.time.LocalDate;

public class BankTransaction {

	@Override
	public String toString() {
		return "[date]=" + date + "; [description]=" + description + ", [amount]=" + amount ;
	}
	public String toCSV() {
		return date+","+amount+","+description;
	}
	
	
	private LocalDate date;
	private String description;
	private float amount;
	
	public BankTransaction(LocalDate date, String description, float amount) {
		super();
		this.date = date;
		this.description = description;
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}

}
