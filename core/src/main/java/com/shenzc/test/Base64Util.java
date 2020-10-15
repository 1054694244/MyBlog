package com.shenzc.test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/7 14:52
 */
public class Base64Util {

    /**
     * 使用base64对字符串进行编码，返回16位字符串
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encoder(String str) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        String encoderStr = encoder.encodeToString(str.getBytes("utf-8"));
        return encoderStr;
    }

    /**
     * 使用base64对已编码字符进行解密，返回原字符串
     * @param encoderStr
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decoder(String encoderStr) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        String decoderStr = new String(decoder.decode(encoderStr), "utf-8");
        return decoderStr;
    }
}
