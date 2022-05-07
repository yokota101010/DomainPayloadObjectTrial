package com.example.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.entity.Book;

/** ブック情報 Service */
@Service
public class BookService {

	/** ブック情報（全検索） */
	public Map<String, Book> searchAll() {
		//Map<String, Book>を作成
		Map<String, Book> bookMap = new LinkedHashMap<>();

		//Book1の作成
		Book book1 = new Book(100, "興奮した");

		//Book2の作成
		Book book2 = new Book(5500, "全米が泣いた");

		//bookMapにbookを設定
		bookMap.put("奇跡の星", book1);
		bookMap.put("日本☆沈没", book2);

		return bookMap;
  }
}
