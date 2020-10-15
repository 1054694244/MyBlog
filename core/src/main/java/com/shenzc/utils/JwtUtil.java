package com.shenzc.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.shenzc.entity.User;

import java.util.Date;
import java.util.HashMap;

public class JwtUtil {


    /**
     * 过期时间为一天
     * TODO 正式上线更换为15分钟
     */
    private static final long EXPIRE_TIME = 24*60*60*1000;

    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "joijsdfjlsjfljfljl5135313135";

    private static DecodedJWT jwt;

    /**
     * 生成签名,15分钟后过期
     * @param user
     * @return
     */
    public static String sign(User user){
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带user相关信息生成签名
        return JWT.create()
                .withHeader(header)
                .withClaim("username",user.getUsername())
                .withClaim("userId",user.getUserId())
                .withClaim("phone",user.getPhone())
                .withClaim("email",user.getEmail())
                .withClaim("roleId",user.getRoleId())
                .withExpiresAt(date)
                .sign(algorithm);
    }


    public static boolean verity(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            jwt = verifier.verify(token);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public static String usesrname(){
        return jwt.getClaim("username").asString();
    }

    public static String userId(){
        return jwt.getClaim("userId").asString();
    }
}