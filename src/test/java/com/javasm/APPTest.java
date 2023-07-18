package com.javasm;

import com.javasm.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/18 10:49
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class APPTest {
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 测试key-value
     */
    @Test
    public void test1() {
        // 简单k-v操作
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("str1","val1");
        System.out.println(valueOperations.get("str1"));

        // 有过期时间的设置
        valueOperations.set("str2","大蠢狗",20, TimeUnit.SECONDS);
        System.out.println(valueOperations.get("str2"));

        // value值为对象
        valueOperations.set("key3",new Student(1,"渣机"));
        System.out.println(valueOperations.get("key3"));
    }

    /**
     * 测试hash
     */
    @Test
    public void test2(){

    }
}