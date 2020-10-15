/*
package com.shenzc.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.yvan.springmvc.HttpUtils;
import lombok.val;
import org.joda.time.DateTime;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

public class JwtHelper {
    private static final String USER_ID = "u";
    private static final String TOKEN_VERSION = "v";
    private static final String USER_TYPE = "t";
    private static final String STAFF_NAME = "n";

    public static final String AUTH_HEADER_NAME = "Authorization";
    public static final String AUTH_COOKIE_NAME = "auth";

    //头部
    private static final Map<String, Object> HEADER = new ImmutableMap.Builder<String, Object>()
            .build();

    public static boolean verify(String secret) {
        return verify(getAuthHeader(HttpUtils.currentRequest()), secret);
    }

    */
/**
     * 校验 jwtToken 是否正确
     *//*

    public static boolean verify(String jwtToken, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            verifier.verify(jwtToken);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    */
/**
     * 校验 jwtToken 是否正确
     *//*

    public static boolean verify(String jwtToken, String userId, String tokenVersion, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(USER_ID, userId)
                    .withClaim(TOKEN_VERSION, tokenVersion)
                    .build();
            verifier.verify(jwtToken);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    */
/**
     * 获得 userId
     *//*

    public static String getUserId(String token) {
        if (Strings.isNullOrEmpty(token)) {
            return "";
        }
        Map<String, Claim> m = JWT.decode(token).getClaims();
        return m.get(USER_ID).asString();
    }

    */
/**
     * 获取当前请求的 agentId
     *//*

    public static String getUserId() {
        return getUserId(getAuthHeader(HttpUtils.currentRequest()));
    }

    */
/**
     * 获得 tokenVersion
     *//*

    public static String getTokenVersion(String token) {
        if (Strings.isNullOrEmpty(token)) {
            return "";
        }
        Map<String, Claim> m = JWT.decode(token).getClaims();
        return m.get(TOKEN_VERSION).asString();
    }

    */
/**
     * 获得 userType
     *//*

    public static String getUserType(String token) {
        if (Strings.isNullOrEmpty(token)) {
            return "";
        }
        Map<String, Claim> m = JWT.decode(token).getClaims();
        return m.get(USER_TYPE).asString();
    }

    public static String getUserType() {
        return getUserType(getAuthHeader(HttpUtils.currentRequest()));
    }

    */
/**
     * 获得 EntityId
     *//*

    public static String getStaffName(String token) {
        if (Strings.isNullOrEmpty(token)) {
            return "";
        }
        Map<String, Claim> m = JWT.decode(token).getClaims();
        return m.get(STAFF_NAME).asString();
    }

    */
/**
     * 获取当前请求的 EntityId
     *//*

    public static String getStaffName() {
        return getStaffName(getAuthHeader(HttpUtils.currentRequest()));
    }

    */
/**
     * 获取授权过期时间
     *//*

    public static DateTime getExpireTime(String token) {
        if (Strings.isNullOrEmpty(token)) {
            return null;
        }
        return new DateTime(JWT.decode(token).getExpiresAt().getTime());
    }

    */
/**
     * 获取授权过期时间
     *//*

    public static DateTime getExpireTime() {
        return getExpireTime(getAuthHeader(HttpUtils.currentRequest()));
    }

    public static boolean isLogined() {
        return (getAuthHeader(HttpUtils.currentRequest()) != null);
    }

    public static String getAuthHeader(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(JwtHelper.AUTH_HEADER_NAME);
        if (!Strings.isNullOrEmpty(authorization)) {
            return authorization;
        }
        authorization = HttpUtils.getCookieValue(JwtHelper.AUTH_COOKIE_NAME);
        if (!Strings.isNullOrEmpty(authorization)) {
            return authorization;
        }
        return null;
    }

    */
/**
     * 生成签名
     *//*

    public static String sign(String userAgentId, String tokenVersion, String role, String staffName, String secret, int expireOfMinutes) {

        DateTime now = DateTime.now();

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withHeader(HEADER)
                .withClaim(USER_ID, userAgentId)
                .withClaim(TOKEN_VERSION, tokenVersion)
                .withClaim(STAFF_NAME, staffName)
                .withClaim(USER_TYPE, role)
                .withIssuedAt(now.toDate())
                .withExpiresAt(now.plusMinutes(expireOfMinutes).toDate())
                .sign(algorithm);
    }

    */
/**
     * 对票据进行延期
     *//*

    public static String extendExpireTime(String token, int expireMinutes, String secret) {
        DateTime now = DateTime.now();

        val decode = JWT.decode(token);
        Map<String, Claim> m = decode.getClaims();

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withHeader(HEADER)
                .withClaim(USER_ID, m.get(USER_ID).asString())
                .withClaim(TOKEN_VERSION, m.get(TOKEN_VERSION).asString())
                .withClaim(STAFF_NAME, m.get(STAFF_NAME).asString())
                .withClaim(USER_TYPE, m.get(USER_TYPE).asString())
                .withIssuedAt(decode.getIssuedAt())
                .withExpiresAt(now.plusMinutes(expireMinutes).toDate())
                .sign(algorithm);
    }

    public static String appId() {
        return appId(HttpUtils.currentRequest());
    }

    public static String appId(HttpServletRequest request) {
        return request.getHeader("appId");
    }

    public static String authToken() {
        return authToken(HttpUtils.currentRequest());
    }

    public static String ownerId(HttpServletRequest request) {
        return request.getHeader("ownerId");
    }

    public static String ownerId() {
        return authToken(HttpUtils.currentRequest());
    }

    public static String operator(HttpServletRequest request) {
       try {
           return URLDecoder.decode(request.getHeader("staff"), "UTF-8");
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
           return null;
       }
    }

    public static String xAuthValue(HttpServletRequest request) {
        try {
            return URLDecoder.decode(request.getHeader("X-Auth-Value"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getUserWh() {
        return userWh(HttpUtils.currentRequest());
    }

    public static String userWh(HttpServletRequest request) {
        return request.getHeader("userWh");
    }

    public static String operator() {
        return operator(HttpUtils.currentRequest());
    }

    public static String xAuthValue() {
        return xAuthValue(HttpUtils.currentRequest());
    }

    public static String authToken(HttpServletRequest request) {
        return request.getHeader("authCode");
    }

    public static void main(String[] args) {

        val v = verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0IjoiQSIsInUiOiJBRE1JTl8xIiwidiI6Ik4iLCJleHAiOjE1Mzk2MDMxOTgsImlhdCI6MTUzOTYwMjU5OCwibiI6IueuoeeQhuWRmCJ9._HAu7DCGtYelHH5gIH9HsyePkvsZqDGg-zntUwjxBYc",
                "jztd");

        System.out.println(v);

        String secret = "jzt600998";
        String role = "custom";
        String userId = "CUST_1";
        String staffName = "张三";
        String version = "1";
        String token = sign(userId, version, role, staffName, secret, 6 * 30 * 24 * 60);
        System.out.println("token=" + token);
        System.out.println("getAgentId=" + getUserId(token));
        System.out.println("getTokenVersion=" + getTokenVersion(token));
        System.out.println("getRole=" + getUserType(token));
        System.out.println("verify(token)=" + verify(token, secret));
        System.out.println("verify(token, errorSecret)=" + verify(token, secret + "a"));
        System.out.println("verify(token, errorVersion)=" + verify(token, userId, "0", secret));
        System.out.println("verify(token, right)=" + verify(token, userId, version, secret));
    }


}

*/
