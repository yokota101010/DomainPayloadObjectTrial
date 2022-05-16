package com.example.entity;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class Commodity {

	@Min(1)
	protected int price = -1;
}
