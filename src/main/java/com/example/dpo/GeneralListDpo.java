package com.example.dpo;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.entity.book.Book;
import com.example.entity.user.User;
import com.example.fw.DomainPayloadObject;

import lombok.Data;

@Data
@DomainPayloadObject
public class GeneralListDpo {

	@Valid
	private List<User> userList;

	@Valid
	private Map<String, Book> bookMap;
}
