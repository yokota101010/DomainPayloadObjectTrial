package com.example.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class MusicRepository {

	public Music getMusic(String title) {
		return musicMap.get(title);
	}

	private Map<String, Music> musicMap
		= new HashMap<String, Music>() {{
				put("情熱のバラバラ", new Music("情熱のバラバラ", 90));
				put("リンダリンダリンダ", new Music("リンダリンダリンダ", 180));
			}};
}
