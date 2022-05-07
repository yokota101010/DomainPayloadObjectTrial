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

import com.example.entity.Book;
import com.example.entity.User;
import com.example.form.GeneralListForm;
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


	/** 一覧画面を表示 */
	@GetMapping(value = "/general/list")
	public String displayList(GeneralListForm form, Model model) {

		List<User> userList = userService.searchAll();
		Map<String, Book> bookMap = bookService.searchAll();

		form.setUserList(userList);
		form.setBookMap(bookMap);
		ModelPlus.addAttribute("generalListForm", form, model);

		return "general/list";
  }


	/** 情報更新 */
	@RequestMapping(value = "/general/listUpdate", method = RequestMethod.POST)
	public String listUpdate(@Validated GeneralListForm form, BindingResult bindingResult, Model model) {

		//入力チェック結果確認
		if(bindingResult.hasErrors()) {
			System.out.println("どこかにエラーがありました。");
			return "redirect:/general/list";
		}

		//更新結果画面用にmodelに情報を登録（ModelPlusは使わない）
		model.addAttribute("formServiceUserList", userService.searchAll());
		model.addAttribute("fromFormUserList",form.getUserList());

		model.addAttribute("fromServiceBookMap", bookService.searchAll());
		model.addAttribute("fromFormBookMap", form.getBookMap());

		return "general/result";
	}
}
