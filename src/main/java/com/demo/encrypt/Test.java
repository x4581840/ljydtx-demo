package com.demo.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/10/22 10:29 AM
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        //hzs_user表的user_password字段
        String newPwd = "welcome123";
        String encryptedPassword = encryptedPassword(newPwd);
        System.out.println(encryptedPassword);
    }

    private static String encryptedPassword(String pwd) {
        String password = "";
        try {
            byte[] md5Byte = makeMD5(pwd);
            byte[] byteResutl = new byte[md5Byte.length];
            int j = 0;
            for (byte b : md5Byte) {
                if (b < 0) {
                    int i = b & 0xff;
                    byteResutl[j] = (byte) (i - 256);
                    j++;
                } else {
                    byteResutl[j] = b;
                    j++;
                }
            }
            System.out.println(Arrays.toString(byteResutl));
            int length = new String(Base64.getEncoder().encode(byteResutl), "UTF-8").length();
            password = new String(Base64.getEncoder().encode(byteResutl), "UTF-8").substring(0, length - 2);

        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.getMessage());
        }
        return password;
    }

    public static byte[] makeMD5(String password) {
        MessageDigest md;
        byte[] pwd = null;
        try {
            // 生成一个MD5加密计算摘要
            md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(password.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            pwd = md.digest();
            return pwd;
        } catch (Exception ex) {
            //logger.log(Level.SEVERE, "", ex);
        }
        return pwd;
    }
}
