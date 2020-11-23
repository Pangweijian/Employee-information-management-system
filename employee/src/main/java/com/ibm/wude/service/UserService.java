package com.ibm.wude.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.wude.mapper.UserMapper;
import com.ibm.wude.model.UserModel;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;

	public List<UserModel> getAllUser() {
		return userMapper.getAllUser();
	}

	public UserModel getUserByUsername(String username) {
		return userMapper.getUserModelByUsername(username);
	}

	public boolean getUserModelByUserlogin(UserModel user) {
		if (userMapper.getUserModelByUserlogin(user) != null)
			return true;
		else {
			return false;
		}
	}

	public boolean addUser(UserModel userModel) {
		return userMapper.addUser(userModel);
	}
}
