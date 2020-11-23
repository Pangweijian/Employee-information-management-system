package com.ibm.wude.vo;

/**
 * 封装一个返回 含令牌的用户对象
 */
public class TokenVo {
	// 用户名
	private String username;
	// 令牌名
	private String token;

	public TokenVo(String username, String token) {
		super();
		this.username = username;
		this.token = token;
	}

	@Override
	public String toString() {
		return "\n\t\t\t\"username\": " + "\"" + username + "\"" + ", \n\t\t\t\"token\": " + "\"" + token + "\"";
	}

}
