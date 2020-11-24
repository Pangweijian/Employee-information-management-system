package com.ibm.wude.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.wude.model.Msg;
import com.ibm.wude.model.UserModel;
import com.ibm.wude.service.UserService;
import com.ibm.wude.utils.JwtUtils;
import com.ibm.wude.vo.TokenVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags = "用户信息Controller")
public class UserController {

	@Autowired
	UserService UserService;

	@ApiOperation("获取所有用户信息")
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
	@ApiOperation("通过用户名查询用户信息")
	@GetMapping("/getUserByUsername/{username}")
	public UserModel getUserModelByUsername(@PathVariable("username") String username) {
		return UserService.getUserByUsername(username);
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	@ApiOperation("登录")
	@PostMapping("/getUserModelByUserlogin")
	public String getUserModelByUserlogin(@RequestBody UserModel user) {
		if (UserService.getUserByUsername(user.getUsername()) != null) {
			if (UserService.getUserModelByUserlogin(user) != false) {
				// 密码匹配，发放令牌
				/// 随机生成字符串未userid
				String userid = UUID.randomUUID().toString();
				String token = JwtUtils.sign(userid);
				// 封装令牌对象
				TokenVo tokenVo = new TokenVo(user.getUsername(), token);
				return new Msg(200, "登录成功,令牌已发放", tokenVo).toString();
			}
		} else {
			return new Msg(403, "用户不存在", null).toString();
		}
		return new Msg(403, "密码错误", null).toString();
	}

	/**
	 * 增加用户信息（注册）
	 * 
	 * @param userModel
	 * @return
	 */
	@ApiOperation("注册")
	@PostMapping("/register")
	public boolean addUser(@RequestBody UserModel userModel) {
		if (UserService.getUserByUsername(userModel.getUsername()) == null) {
			return UserService.addUser(userModel);
		} else {
			return false;
		}
	}
}
