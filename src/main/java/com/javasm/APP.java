package com.javasm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.Resource;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/17 17:32
 * @description:
 */
@SpringBootApplication()
@MapperScan("com.javasm.mapper")
@EnableCaching //开启缓存，与redis结合
public class APP {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(APP.class, args);
    }

}
