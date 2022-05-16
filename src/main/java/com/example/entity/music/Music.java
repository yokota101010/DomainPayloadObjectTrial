package com.example.entity.music;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.example.entity.Commodity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Music extends Commodity {

	@Valid
	private MusicId musicId = new MusicId("");

	@NotBlank
	private String title = "";

	private int bpm = 0;

	public Music(MusicId musicId, String title, int bpm, int price) {
		this.musicId = musicId;
		this.title = title;
		this.bpm = bpm;
		super.price = price;
	}

	public Music() {
	}
}
