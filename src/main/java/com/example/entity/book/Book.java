package com.example.entity.book;

import com.example.entity.Commodity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Book extends Commodity {

	private String reputation = "";

	public Book(int price, String reputation) {
		super.price = price;
		this.reputation = reputation;
	}

	public Book() {
	}
}
