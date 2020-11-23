package com.ibm.wude.model;

import com.ibm.wude.vo.TokenVo;

public class Msg {

	private Integer eroCode;
	private String mess;
	private TokenVo tokenVo;

	public Msg(Integer eroCode, String mess, TokenVo tokenVo) {
		super();
		this.eroCode = eroCode;
		this.mess = mess;
		this.tokenVo = tokenVo;
	}

	public Integer getEroCode() {
		return eroCode;
	}

	public void setEroCode(Integer eroCode) {
		this.eroCode = eroCode;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public TokenVo getTokenVo() {
		return tokenVo;
	}

	public void setTokenVo(TokenVo tokenVo) {
		this.tokenVo = tokenVo;
	}

	@Override
	public String toString() {
		if (this.tokenVo != null) {
			return "{\n\t\"eroCode\": " + eroCode + ",\n\t\"mess\": " + "\"" + mess + "\""
					+ ",\n\t\"tokenVo\": [\n\t\t{" + tokenVo + "\n\t\t}\n\t]\n}";
		} else {
			return "{\n\t\"eroCode\": " + eroCode + ",\n\t\"mess\": " + "\"" + mess + "\"" + ",\n\t\"tokenVo\": "
					+ "null" + "\n}";
		}
	}
}
