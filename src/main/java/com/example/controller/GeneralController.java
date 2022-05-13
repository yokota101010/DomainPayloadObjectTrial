package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dpo.GeneralListDpo;
import com.example.entity.book.Book;
import com.example.entity.music.MusicRepository;
import com.example.entity.user.User;
import com.example.fw.ModelPlus;
import com.example.service.BookService;
import com.example.service.UserService;

@Controller
public class GeneralController {

	/** ユーザー情報Service */
	@Autowired
	private UserService userService;

	/** ブック情報Service */
	@Autowired
	private BookService bookService;

	/** ミュージックRepository */
	@Autowired
	private MusicRepository repository;

	/** 一覧画面を表示 */
	@GetMapping(value = "/general/list")
	public String displayList(GeneralListDpo dpo, Model model) {

		List<User> userList = userService.searchAll();
		Map<String, Book> bookMap = bookService.searchAll();

		dpo.setUserList(userList);
		dpo.setBookMap(bookMap);
		dpo.setMusicRepository(repository);
		ModelPlus.addAttribute("generalListDpo", dpo, model);

		return "general/list";
  }

	/** 情報更新 */
	@RequestMapping(value = "/general/listUpdate", method = RequestMethod.POST)
	public String listUpdate(@Validated GeneralListDpo dpo, BindingResult bindingResult, Model model) {

		//入力チェック結果確認
		if(bindingResult.hasErrors()) {
			System.out.println("どこかにエラーがありました。");
			return "redirect:/general/list";
		}

		//更新結果画面用にmodelに情報を登録（参照だけなのでModelPlusは使わない）
		model.addAttribute("formServiceUserList", userService.searchAll());
		model.addAttribute("fromDpoUserList",dpo.getUserList());

		model.addAttribute("fromServiceBookMap", bookService.searchAll());
		model.addAttribute("fromDpoBookMap", dpo.getBookMap());

		model.addAttribute("fromDIMusicRepository", repository);
		model.addAttribute("fromDpoMusicRepository", dpo.getMusicRepository());

		return "general/result";
	}
}
