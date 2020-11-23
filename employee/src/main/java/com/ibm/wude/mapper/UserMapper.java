package com.ibm.wude.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ibm.wude.model.UserModel;

@Mapper
public interface UserMapper {
	public boolean addUser(UserModel user);

//	public boolean updateUser(UserModel userModel);

	public UserModel getUserModelByUsername(String username);

	public UserModel getUserModelByUserlogin(UserModel user);

	public List<UserModel> getAllUser();
}
