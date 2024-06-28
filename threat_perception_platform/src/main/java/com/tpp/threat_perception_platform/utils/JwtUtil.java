package com.tpp.threat_perception_platform.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtil {

    //有效期为
    public static final Long JWT_TTL = 60 * 60 * 1000L;// 60 * 60 *1000  一个小时
    //设置秘钥明文
    public static final String JWT_KEY = "hsj9527";

    public static String getUUID() {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }

    /**
     * 生成jtw
     *
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw
     *
     * @param subject   token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }

    public static boolean isTokenExpired(Claims claims, long refreshThreshold) {
        Date expiration = claims.getExpiration();
        Date now = new Date();
        // 计算两个日期之间的差异，转换为分钟
        long diffInMinutes = Duration.between(now.toInstant(), expiration.toInstant()).toMinutes();

        return diffInMinutes < refreshThreshold;
    }

    /**
     * 创建token
     *
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)      // 主题 可以是JSON数据
                .setIssuer("hsj")         // 签发者
                .setIssuedAt(now)         // 签发时间
                .signWith(signatureAlgorithm, secretKey); //使用HS256对称加密算法签名, 第二个参数为秘钥
                //.setExpiration(expDate);
    }

    /**
     * 解析
     *
     * @param jwt
     * @return bplustree
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    public static void main(String[] args) throws Exception {
//        String jwt = createJWT("123456");
//        System.out.println(jwt);
//        Claims claims = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxZGMyMGE4OTYxMzA0MmU0ODI1NzRhYWM3ZTMyZDViOSIsInN1YiI6IjEyMzQ1NiIsImlzcyI6ImhzaiIsImlhdCI6MTcxNjE4Njg0MywiZXhwIjoxNzE2MTkwNDQzfQ.YPGngc73oAvp1qxWNKxfIdqLdA6VxiO8U4Yt-2ldAas");
//        System.out.println(claims);
//        System.out.println(claims.getSubject());
    }
}
