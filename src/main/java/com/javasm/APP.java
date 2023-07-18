package com.javasm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/17 17:32
 * @description:
 */
@SpringBootApplication
public class APP {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(APP.class, args);
    }

}
