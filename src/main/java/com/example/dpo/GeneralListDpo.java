package com.example.dpo;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.entity.book.Book;
import com.example.entity.music.MusicRepository;
import com.example.entity.user.User;

import lombok.Data;

@Data
public class GeneralListDpo {

	@Valid
	private List<User> userList;

	@Valid
	private Map<String, Book> bookMap;

	private MusicRepository musicRepository;
}
