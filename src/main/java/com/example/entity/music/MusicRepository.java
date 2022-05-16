package com.example.entity.music;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class MusicRepository {

	public Music getMusic(MusicId id) {
		return musicMap.get(id);
	}

	private Map<MusicId, Music> musicMap
		= new HashMap<MusicId, Music>() {{
				put(new MusicId("m001"), new Music(new MusicId("m001"), "情熱のバラバラ", 90, 1000));
				put(new MusicId("m002"), new Music(new MusicId("m002"), "リンダリンダリンダ", 180, 2000));
			}};
}
