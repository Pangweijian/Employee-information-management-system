package com.ibm.wude.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.wude.model.UserModel;
import com.ibm.wude.service.UserService;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	UserService UserService;

	@GetMapping("/getAllUser")
	public List<UserModel> getAllUser() {
		List<UserModel> list = UserService.getAllUser();
		return list;
	}

	/**
	 * 查询用户信息
	 * 
	 * @param username
	 * @return 查询结果
	 */
	@GetMapping("/getUserByUsername/{username}")
	public UserModel getUserModelByUsername(@PathVariable("username") String username) {
//		System.out.println("username:" + username);

		return UserService.getUserByUsername(username);
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/getUserModelByUserlogin")
	public boolean getUserModelByUserlogin(@RequestBody UserModel user) {
//		System.out.println(user.getUsername());
//		System.out.println(UserService.getUserByUsername(user.getUsername()));
		if (UserService.getUserByUsername(user.getUsername()) != null) {
			if (UserService.getUserModelByUserlogin(user) != false) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	/**
	 * 增加用户信息（注册）
	 * 
	 * @param userModel
	 * @return
	 */
	@PostMapping("/register")
	public boolean addUser(@RequestBody UserModel userModel) {
		if (UserService.getUserByUsername(userModel.getUsername()) == null) {
			return UserService.addUser(userModel);
		} else {
			return false;
		}
	}
}
