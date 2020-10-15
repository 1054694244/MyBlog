package com.shenzc.test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/7 15:26
 */
public class DESUtil {

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "DES";
    /**
     * 加密/解密算法-工作模式-填充模式
     */
    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";
    /**
     * 默认编码
     */
    private static final String CHARSET = "utf-8";

    public static void main(String[] args) {
        //偏移变量，固定占8位字节
        String iv = "54a6cc76";
        //密码
        String password = "54a6cc76-e700-a1b2-fa3d-fb966efb7578";
        //加密
        String data = "1234567890";
        String encrypt = encrypt(password, iv, data);
        System.out.println(encrypt);
        //解密
        String decrypt = decrypt(password, iv, encrypt);
        System.out.println(decrypt);

    }

    /**
     * 生成key
     *
     * @param password
     * @return
     * @throws Exception
     */
    private static Key generateKey(String password) throws Exception {
        DESKeySpec dks = new DESKeySpec(password.getBytes(CHARSET));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(dks);
    }


    /**
     * DES加密字符串
     *
     * @param password 加密密码，长度不能够小于8位
     * @param data 待加密字符串
     * @param ivParam 偏移向量
     * @return 加密后内容
     */
    public static String encrypt(String password, String ivParam, String data) {
        if (password== null || password.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        if (data == null){
            return null;
        }
        try {
            Key secretKey = generateKey(password);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(ivParam.getBytes(CHARSET));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] bytes = cipher.doFinal(data.getBytes(CHARSET));

            BASE64Encoder encoder = new BASE64Encoder();
            return new String(encoder.encode(bytes));

        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    /**
     * DES解密字符串
     *
     * @param password 解密密码，长度不能够小于8位
     * @param ivParam 偏移向量
     * @param data 待解密字符串
     * @return 解密后内容
     */
    public static String decrypt(String password, String ivParam, String data) {
        if (password== null || password.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        if (data == null){
            return null;
        }
        try {
            Key secretKey = generateKey(password);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(ivParam.getBytes(CHARSET));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            BASE64Decoder decoder = new BASE64Decoder();
            return new String(cipher.doFinal(decoder.decodeBuffer(data)), CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

}
