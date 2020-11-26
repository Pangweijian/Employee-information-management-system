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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags = "用户信息Controller")
public class UserController {

	@Autowired
	UserService UserService;

	@ApiOperation(value = "获取所有用户信息", notes = "不必传入参数")
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
	@ApiOperation(value = "通过用户名查询用户信息")
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
	@ApiOperation(value = "登录", notes = "传入一个POJO（JSON格式）,其中“username”(用户名）和“password”（密码）是必须的")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "path", required = true),
			@ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "path", required = true) })
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
	@ApiOperation(value = "注册", notes = "传入一个POJO（JSON格式）,其中“username”(用户名）、“password”（密码）是必须的")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "path", required = true),
			@ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "path", required = true) })
	@PostMapping("/register")
	public Msg addUser(@RequestBody UserModel userModel) {
		if (UserService.getUserByUsername(userModel.getUsername()) == null) {
			UserService.addUser(userModel);
			return new Msg(200, "注册成功", null);
		} else {
			return new Msg(403, "用户已存在，注册失败", null);
		}
	}
}
