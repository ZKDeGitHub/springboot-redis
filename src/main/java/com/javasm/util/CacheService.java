package com.javasm.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/20 15:40
 * @description: redis的存取方法类，可能会多处用到，故抽出来
 *
 */
@Component
public class CacheService {

   // redis中存储token的目录名称
   public static final String TOKEN_CACHE_NAME = "token:";

   @Resource
   private RedisTemplate redisTemplate;

   /**
    * 根据key值获取到对应的redis中的value
    * @param key
    * @return
    */
   public String getRedisValue(String key){
      ValueOperations valueOperations = redisTemplate.opsForValue();
      Object o = valueOperations.get(key);
      return (String) o;
   }

   public void  set(String user_token, String redisToken, Integer i, TimeUnit minutes){
      
      // redis中的token未过期时，延长时间
      if(redisToken == null){
         redisTemplate.expire(user_token,i,minutes);
      }

      // 正常的设置过期时间
      ValueOperations valueOperations = redisTemplate.opsForValue();
      valueOperations.set(user_token,redisToken,i,minutes);

   }
}
