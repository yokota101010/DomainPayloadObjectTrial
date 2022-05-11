package com.example.entity;

import lombok.Data;

@Data
public class Book {

	private int price = 0;
	private String reputation = "";

	public Book(int price, String reputation) {
		this.price = price;
		this.reputation = reputation;
	}

	public Book() {
	}
}
