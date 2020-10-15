package com.shenzc.test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/7 14:59
 */
public class SHAUtil {

    /**
     * 使用md5对字符串进行加密,生成32位字符串，不可以通过加密字符串获取到原字符串，不可逆。
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encoder(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA");
        byte[] digest = md.digest(str.getBytes());
        BigInteger bigInteger = new BigInteger(1, digest);
        return  bigInteger.toString(16);
    }

}
