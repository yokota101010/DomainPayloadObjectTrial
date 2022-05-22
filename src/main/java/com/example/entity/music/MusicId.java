package com.example.entity.music;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MusicId {

	@NotBlank
	private String id = "";

	public MusicId(String id) {
		this.id = id;
	}

	public MusicId() {
	}

	@Override
	public String toString() {
		return this.id;
	}
}
