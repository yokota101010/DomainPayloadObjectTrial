package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Career;
import com.example.entity.User;

/**
 * ユーザー情報 Service
 */
@Service
public class UserService {

	/** ユーザー情報（全検索） */
	public List<User> searchAll() {

		//User1の作成
		List<Career> list1 = new ArrayList<>();
		list1.add(new Career(0, "1977/05/13", "生まれる"));
		list1.add(new Career(1, "2003/04/01", "入社"));
		User user1 = new User("user1@sample.co.jp", "高木", 25, list1);

		//User2の作成
		List<Career> list2 = new ArrayList<>();
		list2.add(new Career(0, "1983/06/23", "生まれる"));
		list2.add(new Career(1, "2013/10/01", "Oxfordへ入学"));
		User user2 = new User("user2@sample.co.jp", "中本", 30, list2);

		//List<User>を作成
		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);

		return userList;
  }
}
