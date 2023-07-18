package com.javasm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/18 9:56
 * @description:
 */
@Configuration
public class RedisConfig {

    /**
     * 修改序列化的方式
     * 自定义一个RedisTemplate的Bean不再使用自动化的RedisTemplate的Bean
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){

        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        //key采用String的序列化方式
        template.setKeySerializer(RedisSerializer.string());
        //value序列化方式采用jackson
        template.setValueSerializer(RedisSerializer.json());
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(RedisSerializer.string());
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(RedisSerializer.json());
        template.setConnectionFactory(factory);
        return template;
    }
}
