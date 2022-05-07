package com.example.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class Career {

	private int id;

	@NotNull
	private String fromDate;

	@NotBlank
	@Length(max=10)
	private String contents;

	public Career(int id, String fromDate, String contents) {
		this.id = id;
		this.fromDate = fromDate;
		this.contents = contents;
	}

	public Career() {
	}
}
