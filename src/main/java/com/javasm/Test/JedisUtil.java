package com.javasm.Test;


/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/17 16:51
 * @description:
 */

public class JedisUtil {
/*
   private static JedisPool jedisPool;
*/

/*   static {
      JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
      jedisPoolConfig.setMaxTotal(50);
      jedisPoolConfig.setMaxIdle(10);
      jedisPool = new JedisPool(jedisPoolConfig,"localhost",6379);
   }*/

   /**
    * 获取连接池
    * @return
    */
/*   public static Jedis getJedis() {
      Jedis resource = jedisPool.getResource();
      return resource;
   }*/

   /**
    * 关闭连接池
    * @param jedis
    */
/*   public static void close(Jedis jedis) {
      if (jedis != null) {
         jedis.close();
      }
   }*/
}
