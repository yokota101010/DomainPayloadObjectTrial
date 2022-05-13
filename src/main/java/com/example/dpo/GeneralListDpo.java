package com.example.dpo;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.entity.Book;
import com.example.entity.MusicRepository;
import com.example.entity.User;

import lombok.Data;

@Data
public class GeneralListDpo {

	@Valid
	private List<User> userList;

	@Valid
	private Map<String, Book> bookMap;

	private MusicRepository musicRepository;
}
