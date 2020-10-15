package com.shenzc.test;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/7 14:44
 */
public class Test1 {

   /* @Test
    public void test1() throws UnsupportedEncodingException {
        // 原始数据
        String str = "Hello World";

        // 加密
        String encoder = Base64Util.encoder(str);
        System.out.println(Base64Util.encoder(str));;

        // 解密
        System.out.println(Base64Util.decoder(encoder));;
    }

    @Test
    public void test2() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println(MD5Util.encoder("Hello World"));;
    }

    @Test
    public void test3() throws InvalidKeyException, NoSuchAlgorithmException {
        String data = "Hello World!";
        System.out.println(HmacUtil.encoder(data));
    }

    @Test
    public void test4() throws Exception{
        String decoder = DESUtil.encoder("你好，武汉");
        System.out.println(decoder);
        System.out.println(DESUtil.decoder(decoder));
    }*/
}
