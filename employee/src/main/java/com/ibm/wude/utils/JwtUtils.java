package com.ibm.wude.utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * 用来生成签名，校验签名，通过签名
 */
public class JwtUtils {
	// 令牌有效时间
	private final static long EXPIRE_TIME = 5 * 60 * 1000;
	// 密钥
	private final static String SECRECT = "Tyler_Yue_key";

	/**
	 * 创建令牌
	 */
	public static String sign(String userId) {
		// 构建失效时钟
		Date exipre_date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		// 创建令牌
		JWTCreator.Builder builder = JWT.create();
		// 给jwt令牌playload中放入发令牌放的用户
		// 给userid用户发令牌
		builder.withAudience(userId);
		// 设置令牌失效时间
		builder.withExpiresAt(exipre_date);
		// 对令牌密钥进行加密
		Algorithm algorithm = Algorithm.HMAC256(SECRECT);
		String sign = builder.sign(algorithm);
		return sign;// 返回令牌
	}

	/**
	 * 验证令牌
	 */
	public static boolean verifyToken(String token) {

		try {
			// 生成校验器
			Algorithm algorithm = Algorithm.HMAC256(SECRECT);
			// 校验
			JWTVerifier build = JWT.require(algorithm).build();
			// 无异常则校验成功
			return true;
		} catch (Exception e) {
			throw new RuntimeException("令牌过期");
		}

	}
}