package com.lyl.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 潘淮  on 2018/12/26.<br>
 */
public class MD5Util {

    public static String MD5(String target, String charset) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.reset();
        byte[] bytes = md5.digest(charset == null ? target.getBytes() : target.getBytes(charset));
        StringBuffer stringBuffer = new StringBuffer("");
        for (byte b : bytes) {
            int bt = b & 0xff;
            if (bt < 16) {
                stringBuffer.append(0);
            }
            stringBuffer.append(Integer.toHexString(bt));
        }
        return stringBuffer.toString();
    }
}
