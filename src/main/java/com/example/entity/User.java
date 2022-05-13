package com.example.entity;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * ユーザー情報 Entity
 */
@Data
public class User {

	@NotBlank
	@Email
	private String id = "";

	@NotBlank
	private String name = "";

	@Min(value=20)
	@Max(value=65)
	private int age = -1;

	@NotBlank
	private String favoriteSong = "";

	@Valid
	private List<Career> careers = null;

	public User(String id, String name, int age, String favoriteSong, List<Career> careers) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.favoriteSong =favoriteSong;
		this.careers = careers;
	}

	public User() {
	}
}
