package com.example.entity;

import lombok.Data;

@Data
public class Music {

	private String title;
	private int bpm;

	public Music(String title, int bpm) {
		this.title = title;
		this.bpm = bpm;
	}

	public Music() {
	}
}
