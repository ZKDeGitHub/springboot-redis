package com.javasm.Test;

import java.util.HashMap;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/17 16:22
 * @description: Jedis的基本使用
 */

public class JedisTest {
    public static void main(String[] args) {
        // 创建jedis对象 默认ip是localhost 端口号是6379
/*        Jedis jedis = new Jedis("localhost", 6379);*/

        // string
/*        jedis.set("str1","ccc");
        System.out.println(jedis.get("str1"));
        jedis.mset("str2","aaa","str3","bbb");
        System.out.println(jedis.mget("str2", "str3"));*/

        // hash
/*      hset key field value   将哈希表key中的字段field的值设为value
        hget  key field          获取存储在哈希表中指定字段field的值
        hmset  key field1 value1 [field2 value2]  同时将多个field-value值设置给key
        hmget key field1 [field2]    获取多个给定字段的值
        hdel key field1 [field2]      删除一个或者多个哈希表中的字段
        hlen key                   获取哈希表中字段的数量
        del key               删除整个hash(对象)
        hgetall key              获取在哈希表中指定key的所有的字段和值
        hkeys key                获取所有哈希表中的字段
        hvals key                 获取哈希表中所有的值*/

/*        jedis.hset("student","studentName","zs");
        System.out.println(jedis.hget("student", "studentName"));

        HashMap<String, String> map = new HashMap<>();
        map.put("age","11");
        map.put("gender","1");
        jedis.hmset("stu2",map);
        System.out.println(jedis.hmget("stu2", "age", "gender"));*/
    }
}
