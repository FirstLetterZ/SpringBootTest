package com.zpf.demo.user.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zpf.demo.global.Constants;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

public class TokenManager {
    // 过期时间 24 小时
    private static final long EXPIRE_TIME = 60 * 24 * 60 * 1000;
    // 密钥
    private static final String SECRET = "e32tyhtjy7jhee32t35h34hjygt";


    public static String createToken(String userId) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            return JWT.create()
                    .withClaim(Constants.USER_ID, userId)
                    .withExpiresAt(date)  //到期时间
                    .sign(Algorithm.HMAC256(SECRET));
        } catch (Exception e) {
            return null;
        }
    }

    public static Map<String, Claim> decodeTokenInfo(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            //验证并解析 token
            DecodedJWT decodeResult = verifier.verify(token);
            return decodeResult.getClaims();
        } catch (Exception exception) {
            return null;
        }
    }

}
