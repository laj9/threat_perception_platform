package com.tpp.threat_perception_platform.utils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Md5Util {
    private static final String charset = "UTF-8";
    private static final String default_salt = "9527";
    /**
     * 使用MD5算法对字符串进行加密
     *
     * @param password 要加密的字符串
     * @return 加密后的MD5字符串
     */
    public static String encryptMD5(String password, String salt) {
        try {
            if (salt == null){
                salt = default_salt;
            }
            // 将Base64编码的盐转换回字节数组
            byte[] saltBytes = Base64.getDecoder().decode(salt);

            // 创建MD5 MessageDigest实例
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 结合盐值和密码进行哈希
            md.update(saltBytes);
            // 更新输入字符串的字节
            md.update(password.getBytes(charset));

            // 获取哈希结果并转化为16进制字符串
            byte[] digestBytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digestBytes) {
                sb.append(String.format("%02x", b & 0xff));
            }

            // 返回MD5字符串
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }


    }

//    public static void main(String[] args) {
//        String passwordToHash = "123123";
//        System.out.println("MD5 Hash: " + encryptMD5(passwordToHash, "9527"));
//    }
}

