package com.example.entity.user;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.entity.music.MusicId;

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
	private int age = 0;

	@NotNull
	private MusicId favoriteSongId = new MusicId("");

	@Valid
	private List<Career> careers = null;

	public User(String id, String name, int age, MusicId favoriteSongId, List<Career> careers) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.favoriteSongId = favoriteSongId;
		this.careers = careers;
	}

	public User() {
	}
}
