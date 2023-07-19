package com.javasm.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/19 10:15
 * @description:
 */

public class JwtUtil {

   private static String keyVal = "DvNRuK6C+h1P/1LVjU9mZG+pywIGrnuLUR43KRi8b9Q=";

   public static void main(String[] args) {

      //执行结果：DvNRuK6C+h1P/1LVjU9mZG+pywIGrnuLUR43KRi8b9Q=
      //generateKey();


      String token = getToken("001", keyVal, 3);
      //eyJhbGciOiJIUzI1NiJ9.eyJVSUQiOiIwMDEiLCJpYXQiOjE2ODk3MzM3NTQsImV4cCI6MTY4OTczMzkzNH0.ahbZy8JKA8C9YO9V295G-IgbcqxBNBCHm5YLugPP0Ic
      System.out.println(token);
      parseToken(token,keyVal);

   }

   /**
    * 生成秘钥
    * 以便用于加密和解密token
    * 密钥只需生成一次
    */
   private static void generateKey() {
      Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
      String encode = Encoders.BASE64.encode(key.getEncoded());
      System.out.println(encode);
   }
   /**
    * 使用密钥进行加密获取token信息
    *
    * @param uid
    * @param keyStr
    * @param expirMinutes
    * @return
    */
   public static String getToken(String uid, String keyStr, Integer expirMinutes) {
      Map<String, String> map = new HashMap<>();
      map.put("UID", uid);
      Date current = new Date();
      // token的过期时间设置
      // Date的时间单位为毫秒，要想传进来的参数当中分钟来用需要*60*1000
      Date expDate = new Date(current.getTime() + expirMinutes * 60 * 1000);
      Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(keyStr));

      String jws = Jwts.builder()
              .setClaims(map)//设置body签名信息:自定义签名Map
              .setIssuedAt(current)//设置token生效时间
              .setExpiration(expDate) //设置token失效时间
              .signWith(key) //设置密钥
              .compact();
      return jws;
   }

   /**
    * 解析token
    * @param token
    * @param keyStr
    * @return
    */
   public static String parseToken(String token, String keyStr) {
      Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(keyStr));
      try {
//            解析器
         Jws<Claims> jws = Jwts.parserBuilder()
                 .setSigningKey(key)
                 .build()
                 .parseClaimsJws(token);
         System.out.println(jws.getSignature());
         System.out.println(jws.getBody());
         System.out.println(jws.getHeader());
         return jws.getBody().get("UID", String.class);
      } catch (SignatureException e) {
         //签名错误,伪造
         return null;
      } catch (ExpiredJwtException e) {
         //过期
         return null;
      } catch (Exception e) {
         return null;
      }
   }
}
