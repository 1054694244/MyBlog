/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.test;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/7 15:13
 */
public class HmacUtil {

    public static String encoder(String str) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] key = new byte[10];  // 手动生成密钥(十位)
        SecretKey secretKey2 = new SecretKeySpec(key, "HmacMD5");   // 还原密钥
        Mac mac = Mac.getInstance(secretKey2.getAlgorithm());       // 实例化mac
        mac.init(secretKey2);                                       // 初始化mac
        byte[] hmacMD5Bytes = mac.doFinal(str.getBytes());         // 生成摘要

        BigInteger bigInteger = new BigInteger(1, hmacMD5Bytes);
        return bigInteger.toString(16);
    }

}
