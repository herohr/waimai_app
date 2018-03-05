package com.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
    public static class passwordMD5{
        public static String hash(String password){
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.reset();
                md5.update(password.getBytes("UTF-8"));
                byte[] byteArray = md5.digest();
                StringBuilder md5StrBuff = new StringBuilder();
                for (byte aByteArray : byteArray) {
                    if (Integer.toHexString(0xFF & aByteArray).length() == 1)
                        md5StrBuff.append("0").append(Integer.toHexString(0xFF & aByteArray));
                    else
                        md5StrBuff.append(Integer.toHexString(0xFF & aByteArray));
                }
                return md5StrBuff.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return "";
        }
    }
}
