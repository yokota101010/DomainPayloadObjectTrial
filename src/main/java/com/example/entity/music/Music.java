package com.example.entity.music;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Music {

	@Valid
	private MusicId musicId = new MusicId("");

	@NotBlank
	private String title = "";

	private int bpm = 0;

	public Music(MusicId musicId, String title, int bpm) {
		this.musicId = musicId;
		this.title = title;
		this.bpm = bpm;
	}

	public Music() {
	}
}
